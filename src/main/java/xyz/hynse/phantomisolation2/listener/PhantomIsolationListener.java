package xyz.hynse.phantomisolation2.listener;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xyz.hynse.phantomisolation2.PhantomIsolation2;
import xyz.hynse.phantomisolation2.util.FlatFileDatabaseUtil;
import xyz.hynse.phantomisolation2.util.PhantomIsolationSchedulerUtil;

public class PhantomIsolationListener implements Listener {

    private final PhantomIsolation2 plugin;

    public PhantomIsolationListener(PhantomIsolation2 plugin) {
        this.plugin = plugin;
        start();
    }

    private void start() {
        PhantomIsolationSchedulerUtil.runAsyncScheduler(plugin, this::tick, PhantomIsolation2.taskInitialDelayTick, PhantomIsolation2.taskInitialDelayTick);
    }

    private void tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (FlatFileDatabaseUtil.getPlayerIsolationStatus(player)) {
                int timeSinceRest = player.getStatistic(Statistic.TIME_SINCE_REST);
                if (timeSinceRest >= 24000) {
                    player.setStatistic(Statistic.TIME_SINCE_REST, 0);
                }
            }
        }
    }
}
