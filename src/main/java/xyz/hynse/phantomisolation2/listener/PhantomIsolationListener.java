package xyz.hynse.phantomisolation2.listener;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xyz.hynse.phantomisolation2.PhantomIsolation2;
import xyz.hynse.phantomisolation2.util.PhantomIsolationSchedulerUtil;


public class PhantomIsolationListener implements Listener {

    public static void start() {
        PhantomIsolationSchedulerUtil.runAsyncScheduler(PhantomIsolation2.instance, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (PhantomIsolation2.databaseUtil.getPlayerIsolationStatus(player)) {
                    player.setStatistic(Statistic.TIME_SINCE_REST, 0);
                }
            }
        }, 1, 10); // 50 seconds = 1000 ticks
    }
}