package me.psikuvit.betterstructures.Commands;

import me.psikuvit.betterstructures.BetterStructure;
import org.bukkit.command.CommandSender;

public abstract class CommandAbstract {
    protected BetterStructure plugin;

    public CommandAbstract(final BetterStructure plugin) {
        this.plugin = plugin;
    }

    public abstract void executeCommand(final String[] args, final CommandSender p1);

    public abstract String correctArg();

    public abstract boolean onlyPlayer();

    public abstract int requiredArg();
}
