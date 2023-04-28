package xyz.hynse.phantomisolation2.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import space.arim.morepaperlib.MorePaperLib;
import xyz.hynse.phantomisolation2.PhantomIsolation2;

import java.time.Duration;

public class PhantomIsolationScheduler {
    private final MorePaperLib morePaperLib;


    public PhantomIsolationScheduler(MorePaperLib morePaperLib) {
        this.morePaperLib = morePaperLib;
    }

    public void start() {
        morePaperLib.scheduling().asyncScheduler().runAtFixedRate(() -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (PhantomIsolation2.instance.getDatabaseManager().getPlayerIsolationStatus(player)) {
                    int timeSinceRest = player.getStatistic(Statistic.TIME_SINCE_REST);
                    if (timeSinceRest >= 24000) {
                        player.setStatistic(Statistic.TIME_SINCE_REST, 0);
                    }
                }
            }
        }, Duration.ZERO, Duration.ofSeconds(1));
    }

}
