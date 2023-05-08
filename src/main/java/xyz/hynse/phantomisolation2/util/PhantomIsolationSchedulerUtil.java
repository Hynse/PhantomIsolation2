package xyz.hynse.phantomisolation2.util;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;
public class PhantomIsolationSchedulerUtil {

    private static Boolean IS_FOLIA = null;

    private static boolean tryFolia() {
        try {
            Bukkit.getAsyncScheduler();
            return true;
        } catch (Throwable ignored) {
        }
        return false;
    }

    public static Boolean isFolia() {
        if (IS_FOLIA == null) IS_FOLIA = tryFolia();
        return IS_FOLIA;
    }

    public static void runAsyncScheduler(Plugin plugin, Runnable runnable, int initialDelayTicks, int periodTicks) {
        if (isFolia()) {
            Bukkit.getAsyncScheduler().runAtFixedRate(plugin, (task) -> runnable.run(), initialDelayTicks, periodTicks, TimeUnit.MILLISECONDS);
        } else {
            Bukkit.getScheduler().runTaskTimer(plugin, runnable, initialDelayTicks * 20L, periodTicks * 20L);
        }
    }
}
