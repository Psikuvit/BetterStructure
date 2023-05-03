package me.psikuvit.betterstructures.Listeners;

import me.psikuvit.betterstructures.BetterStructure;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType() != Material.BEDROCK) {
            return;
        }

        Player p = e.getPlayer();
        File file = BetterStructure.getPlugin().getStructureHandler().getPlayerFile(p);

        Location loc = p.getLocation();

        try {
            // Read the block data from the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String[] coords = parts[0].split(",");

                int x = Integer.parseInt(coords[0]);
                int y = Integer.parseInt(coords[1]);
                int z = Integer.parseInt(coords[2]);

                Material type = Material.getMaterial(parts[1]);

                Block block = Objects.requireNonNull(loc.getWorld()).getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z);
                if (block.getType() == Material.BEDROCK) {
                    continue;
                }
                block.setType(type);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
