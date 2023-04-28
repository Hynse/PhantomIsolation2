package xyz.hynse.phantomisolation2;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.phantomisolation2.command.PhantomIsolationCommand;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationListener;
import xyz.hynse.phantomisolation2.util.FlatFileDatabaseUtil;

public class PhantomIsolation2 extends JavaPlugin {
    public static PhantomIsolation2 instance;
    public static String phantomisolationMessageReloadConfig;
    public static String phantomisolationMessageReloadConfigError;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        register();
        reload();
        FlatFileDatabaseUtil.loadData();
    }
    public void reload() {
        saveDefaultConfig();
        reloadConfig();
        phantomisolationMessageReloadConfig = getConfig().getString("phatomisolationreload-command.messages.relaoad-config");
        phantomisolationMessageReloadConfigError = getConfig().getString("phatomisolationreload-command.messages.relaoad-config-error");
    }
    private void register() {
        getCommand("phantomisolation").setExecutor(new PhantomIsolationCommand(this));
        getCommand("phantomisolationreload").setExecutor(new PhantomIsolationCommand(this));
        getServer().getPluginManager().registerEvents(new PhantomIsolationListener(this), this);
    }
}
