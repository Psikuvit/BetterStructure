package me.psikuvit.betterstructures.Commands;

import me.psikuvit.betterstructures.BetterStructure;
import me.psikuvit.betterstructures.Commands.args.SaveArg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Commands implements CommandExecutor {
    private final Map<String, CommandAbstract> commandAbstractMap;

    public Commands(BetterStructure plugin) {
        commandAbstractMap = new HashMap<>();
        commandAbstractMap.put("save", new SaveArg(plugin));

    }

    public boolean onCommand(final @NotNull CommandSender commandSender, final @NotNull Command command, final @NotNull String label, String[] args) {
        final String subCommand = args[0];
        boolean found = false;
        for (final String cmdAlias : this.commandAbstractMap.keySet()) {
            if (cmdAlias.equalsIgnoreCase(subCommand)) {
                final int argsCount = args.length - 1;
                final boolean isSenderPlayer = commandSender instanceof Player;
                final CommandAbstract cmd = this.commandAbstractMap.get(cmdAlias);
                if (argsCount > cmd.requiredArg()) {
                    commandSender.sendMessage("§cCorrect usage: §e" + cmd.correctArg());
                    return true;
                }
                if (argsCount < cmd.requiredArg()) {
                    commandSender.sendMessage("§cCorrect usage: §e" + cmd.correctArg());
                    return true;
                }
                if (!isSenderPlayer && cmd.onlyPlayer()) {
                    commandSender.sendMessage("§cYou need to be a player");
                    return true;
                }
                args = this.move(args);
                cmd.executeCommand(args, commandSender);
                found = true;
                break;
            }
        }
        if (!found) {
            commandSender.sendMessage("§cUnknown command");
        }
        return true;
    }

    private String[] move(final String[] args) {
        final String[] result = new String[args.length - 1];
        System.arraycopy(args, 1, result, 0, args.length - 1);
        return result;
    }
}
