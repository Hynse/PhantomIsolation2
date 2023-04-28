package xyz.hynse.phantomisolation2.util;

import org.bukkit.entity.Player;

public interface DatabaseUtil {
    boolean getPlayerIsolationStatus(Player player);

    void setPlayerIsolationStatus(Player player, boolean isEnabled);

    void loadData();
}
