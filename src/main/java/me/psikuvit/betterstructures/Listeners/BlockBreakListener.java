package me.psikuvit.betterstructures.Listeners;

import me.psikuvit.betterstructures.BetterStructure;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BlockBreakListener implements Listener {


    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() != Material.BEDROCK) {
            return;
        }
        Player p = e.getPlayer();
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
                            block.setType(Material.AIR);
                        }
                    }
                }
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
