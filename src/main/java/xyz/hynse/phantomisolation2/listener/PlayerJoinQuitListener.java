package xyz.hynse.phantomisolation2.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.hynse.phantomisolation2.database.DatabaseManager;

public class PlayerJoinQuitListener implements Listener {
    private final DatabaseManager databaseManager;

    public PlayerJoinQuitListener(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        databaseManager.loadPlayerIsolationStatus(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        databaseManager.savePlayerIsolationStatus(event.getPlayer());
    }
}
