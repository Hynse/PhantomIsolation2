package xyz.hynse.phantomisolation2.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

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

    public static void runAsyncScheduler(Plugin plugin, Consumer<Player> playerTask, int initialDelayTicks, int periodTicks) {
        if (isFolia()) {
            Bukkit.getAsyncScheduler().runAtFixedRate(plugin, task -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    playerTask.accept(player);
                }
            }, initialDelayTicks, periodTicks, TimeUnit.SECONDS);
        } else {
            Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    playerTask.accept(player);
                }
            }, initialDelayTicks * 20L, periodTicks * 20L);
        }
    }
}
