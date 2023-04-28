package xyz.hynse.phantomisolation2;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.phantomisolation2.command.PhantomIsolationCommand;
import xyz.hynse.phantomisolation2.command.ReloadCommand;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationListener;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationTabCompleterListener;
import xyz.hynse.phantomisolation2.util.DatabaseUtil;
import xyz.hynse.phantomisolation2.util.FlatFileDatabaseUtil;
import xyz.hynse.phantomisolation2.util.MySQLDatabaseUtil;

import java.sql.SQLException;

public class PhantomIsolation2 extends JavaPlugin {
    public static PhantomIsolation2 instance;
    public static String phantomisolationMessageReloadConfig;
    public static String phantomisolationMessageReloadConfigError;
    public static String phantomisolationMessageStatus;
    public static String phantomisolationMessageStatusEnabled;
    public static String phantomisolationMessageStatusDisabled;
    public static String phantomisolationMessageNotPlayer;
    public static String phantomisolationMessageEnabled;
    public static String phantomisolationMessageDisable;
    public static int taskInitialDelayTick;
    public static int taskPeriodTick;
    public static String phantomisolationmessageDatabaseFailLoad;
    public static String phantomisolationmessageDatabaseFailSave;
    public static String phantomisolationMessageUsage;
    public static DatabaseUtil databaseUtil;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        register();
        reload();
        initDatabase();
    }

    private void initDatabase() {
        String dataType = getConfig().getString("database.datatype");
        if ("mysql".equalsIgnoreCase(dataType)) {
            String address = getConfig().getString("address");
            String user = getConfig().getString("user");
            String password = getConfig().getString("password");
            String database = getConfig().getString("database");
            try {
                databaseUtil = new MySQLDatabaseUtil(address, user, password, database);
            } catch (SQLException e) {
                getLogger().severe("Failed to connect to MySQL database!");
                e.printStackTrace();
            }
        } else {
            databaseUtil = new FlatFileDatabaseUtil();
        }
        databaseUtil.loadData();
    }

    public void reload() {
        saveDefaultConfig();
        reloadConfig();
        taskInitialDelayTick = getConfig().getInt("task.initial-delay-tick");
        taskPeriodTick = getConfig().getInt("task.period-tick");
        phantomisolationMessageReloadConfig = getConfig().getString("phatomisolationreload-command.messages.reload-config");
        phantomisolationMessageReloadConfigError = getConfig().getString("phatomisolationreload-command.messages.reload-config-error");
        phantomisolationMessageStatus = getConfig().getString("phatomisolation-command.messages.status");
        phantomisolationMessageStatusEnabled = getConfig().getString("phatomisolation-command.messages.status-enabled");
        phantomisolationMessageStatusDisabled = getConfig().getString("phatomisolation-command.messages.status-disabled");
        phantomisolationMessageNotPlayer = getConfig().getString("phatomisolation-command.messages.not-player");
        phantomisolationMessageEnabled = getConfig().getString("phatomisolation-command.messages.enabled");
        phantomisolationMessageDisable = getConfig().getString("phatomisolation-command.messages.disable");
        phantomisolationMessageUsage = getConfig().getString("phatomisolation-command.messages.usage");
        String dataType = getConfig().getString("database.datatype");
        if ("mysql".equalsIgnoreCase(dataType)) {
            phantomisolationmessageDatabaseFailLoad = getConfig().getString("database.mysql-messages.fail-load");
            phantomisolationmessageDatabaseFailSave = getConfig().getString("database.mysql-messages.fail-save");
        } else {
            phantomisolationmessageDatabaseFailLoad = getConfig().getString("database.flatfile-messages.fail-load");
            phantomisolationmessageDatabaseFailSave = getConfig().getString("database.flatfile-messages.fail-save");
        }

    }
    private void register() {
        getCommand("phantomisolation").setExecutor(new PhantomIsolationCommand());
        getCommand("phantomisolationreload").setExecutor(new ReloadCommand());
        getCommand("phantomisolation").setTabCompleter(new PhantomIsolationTabCompleterListener());
        getServer().getPluginManager().registerEvents(new PhantomIsolationListener(this), this);
    }
}
