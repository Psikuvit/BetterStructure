package me.psikuvit.betterstructures.Commands.args;

import me.psikuvit.betterstructures.BetterStructure;
import me.psikuvit.betterstructures.Commands.CommandAbstract;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveArg extends CommandAbstract {

    public SaveArg(BetterStructure plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender p1) {
        Player p = (Player) p1;
        File file = BetterStructure.getPlugin().getStructureHandler().getPlayerFile(p);
        int radius = 10; // radius in blocks around player
        Location playerLoc = p.getLocation();
        int playerX = playerLoc.getBlockX();
        int playerY = playerLoc.getBlockY();
        int playerZ = playerLoc.getBlockZ();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int x = playerX - radius; x <= playerX + radius; x++) {
                for (int y = playerY - radius; y <= playerY + radius; y++) {
                    for (int z = playerZ - radius; z <= playerZ + radius; z++) {
                        Location loc = new Location(p.getWorld(), x, y, z);
                        if (loc.distance(playerLoc) <= radius) {
                            Block block = loc.getBlock();
                            writer.write(x - playerX + "," + (y - playerY) + "," + (z - playerZ) + ":" + block.getType().name() + "\n");
                        }
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String correctArg() {
        return "/structure save";
    }

    @Override
    public boolean onlyPlayer() {
        return true;
    }

    @Override
    public int requiredArg() {
        return 0;
    }
}
