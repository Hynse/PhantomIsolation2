package xyz.hynse.phantomisolation2.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.hynse.phantomisolation2.PhantomIsolation2;

public class PhantomSpawnListener implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.PHANTOM) {
            Phantom phanntom = (Phantom) event.getEntity();
            for (Player player : phanntom.getWorld().getPlayers()) {
                if (PhantomIsolation2.databaseUtil.getPlayerIsolationStatus(player)) {
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }
}