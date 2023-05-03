package me.psikuvit.betterstructures.Utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class StructureHandler {

    private final File dataFolder;

    public StructureHandler(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    public File getPlayerFile(Player player) {
        UUID uuid = player.getUniqueId();
        return new File(dataFolder, "structures/" + uuid + ".yml");
    }

    public void createPlayerFile(Player player) {
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File playerFile = getPlayerFile(player);
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
