package xyz.hynse.phantomisolation2;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.phantomisolation2.command.PhantomIsolationCommand;
import xyz.hynse.phantomisolation2.command.ReloadCommand;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationListener;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationTabCompleterListener;
import xyz.hynse.phantomisolation2.util.FlatFileDatabaseUtil;

public class PhantomIsolation2 extends JavaPlugin {
    public static PhantomIsolation2 instance;
    public static String phantomisolationMessageReloadConfig;
    public static String phantomisolationMessageReloadConfigError;
    public static String phantomisolationMessageStatus;
    public static String phantomisolationMessageNotPlayer;
    public static String phantomisolationMessageEnabled;
    public static String phantomisolationMessageDisable;
    public static int taskInitialDelayTick;
    public static int taskPeriodTick;
    public static String phantomisolationmessageDatabaseFailLoad;
    public static String phantomisolationmessageDatabaseFailSave;

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
        taskInitialDelayTick = getConfig().getInt("task.initial-delay-tick");
        taskPeriodTick = getConfig().getInt("task.period-tick");
        phantomisolationMessageReloadConfig = getConfig().getString("phatomisolationreload-command.messages.relaoad-config");
        phantomisolationMessageReloadConfigError = getConfig().getString("phatomisolationreload-command.messages.relaoad-config-error");
        phantomisolationMessageStatus = getConfig().getString("phatomisolation-command.messages.status");
        phantomisolationMessageNotPlayer = getConfig().getString("phatomisolation-command.messages.not-player");
        phantomisolationMessageEnabled = getConfig().getString("phatomisolation-command.messages.enabled");
        phantomisolationMessageDisable = getConfig().getString("phatomisolation-command.messages.disable");
        phantomisolationmessageDatabaseFailLoad = getConfig().getString("database.messages.fail-load");
        phantomisolationmessageDatabaseFailSave = getConfig().getString("database.messages.fail-save");
    }
    private void register() {
        getCommand("phantomisolation").setExecutor(new PhantomIsolationCommand(this));
        getCommand("phantomisolationreload").setExecutor(new ReloadCommand());
        getCommand("phantomisolation").setTabCompleter(new PhantomIsolationTabCompleterListener());
        getServer().getPluginManager().registerEvents(new PhantomIsolationListener(this), this);
    }
}
