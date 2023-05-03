package me.psikuvit.betterstructures.Listeners;

import me.psikuvit.betterstructures.BetterStructure;
import me.psikuvit.betterstructures.Utils.StructureHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    StructureHandler structureHandler = BetterStructure.getPlugin().getStructureHandler();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        structureHandler.createPlayerFile(e.getPlayer());

    }
}
