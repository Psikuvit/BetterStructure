package me.psikuvit.betterstructures;

import me.psikuvit.betterstructures.Commands.Commands;
import me.psikuvit.betterstructures.Listeners.BlockPlaceListener;
import me.psikuvit.betterstructures.Listeners.PlayerJoinListener;
import me.psikuvit.betterstructures.Utils.StructureHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterStructure extends JavaPlugin {

    static BetterStructure plugin;
    public StructureHandler structureHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        structureHandler = new StructureHandler(getDataFolder());

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

        getCommand("structure").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BetterStructure getPlugin() {
        return plugin;
    }

    public StructureHandler getStructureHandler() {
        return structureHandler;
    }
}
