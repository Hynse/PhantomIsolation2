package xyz.hynse.phantomisolation2.listener;

import org.bukkit.event.Listener;
import xyz.hynse.phantomisolation2.PhantomIsolation2;
import xyz.hynse.phantomisolation2.util.PhantomIsolationSchedulerUtil;

public class PhantomIsolationListener implements Listener {

    private final PhantomIsolation2 plugin;

    public PhantomIsolationListener(PhantomIsolation2 plugin) {
        this.plugin = plugin;
        start();
    }

    private void start() {
        PhantomIsolationSchedulerUtil.runAsyncScheduler(plugin, this::tick, 20, 20);
    }

    private void tick() {}
}
