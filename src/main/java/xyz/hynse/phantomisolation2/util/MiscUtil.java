package xyz.hynse.phantomisolation2.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import xyz.hynse.phantomisolation2.PhantomIsolation2;
import xyz.hynse.phantomisolation2.command.PhantomIsolationCommand;
import xyz.hynse.phantomisolation2.command.ReloadCommand;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationListener;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationTabCompleterListener;

import java.sql.SQLException;

public class MiscUtil implements Listener {
    private static final PhantomIsolation2 instance = PhantomIsolation2.instance;
    private final FileConfiguration config = instance.getConfig();
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
    public static String dataType;
    public static String address;
    public static String user;
    public static String password;
    public static String database;
    public static String phantomisolationMessageNoPermission;
    public void reload() {
        instance.saveDefaultConfig();
        instance.reloadConfig();
        taskInitialDelayTick = config.getInt("task.initial-delay-tick");
        taskPeriodTick = config.getInt("task.period-tick");
        phantomisolationMessageReloadConfig = config.getString("phantomisolationreload-command.messages.reload-config");
        phantomisolationMessageReloadConfigError = config.getString("phantomisolationreload-command.messages.reload-config-error");
        phantomisolationMessageStatus = config.getString("phantomisolation-command.messages.status");
        phantomisolationMessageStatusEnabled = config.getString("phantomisolation-command.messages.status_enabled");
        phantomisolationMessageStatusDisabled = config.getString("phantomisolation-command.messages.status_disabled");
        phantomisolationMessageNotPlayer = config.getString("phantomisolation-command.messages.not-player");
        phantomisolationMessageEnabled = config.getString("phantomisolation-command.messages.enabled");
        phantomisolationMessageDisable = config.getString("phantomisolation-command.messages.disable");
        phantomisolationMessageUsage = config.getString("phantomisolation-command.messages.usage");
        phantomisolationMessageNoPermission = config.getString("phantomisolation-command.messages.no-permission");
        dataType = config.getString("database.datatype");
        address = config.getString("database.address");
        user = config.getString("database.user");
        password = config.getString("database.password");
        database = config.getString("database.database");
        if ("mysql".equalsIgnoreCase(dataType)) {
            phantomisolationmessageDatabaseFailLoad = config.getString("database.mysql-messages.fail-load");
            phantomisolationmessageDatabaseFailSave = config.getString("database.mysql-messages.fail-save");
        } else {
            phantomisolationmessageDatabaseFailLoad = config.getString("database.flatfile-messages.fail-load");
            phantomisolationmessageDatabaseFailSave = config.getString("database.flatfile-messages.fail-save");
        }
    }
    public void initDatabase() {
        dataType = config.getString("database.datatype");
        if ("mysql".equalsIgnoreCase(dataType)) {
            address = config.getString("database.address");
            user = config.getString("database.user");
            password = config.getString("database.password");
            database = config.getString("database.database");
            try {
                PhantomIsolation2.databaseUtil = new MySQLDatabaseUtil();
            } catch (SQLException e) {
                instance.getLogger().severe("Failed to connect to MySQL database!");
                e.printStackTrace();
            }
        } else {
            PhantomIsolation2.databaseUtil = new FlatFileDatabaseUtil();
        }
        PhantomIsolation2.databaseUtil.loadData();

    }
    public static void sendMessage(CommandSender sender, String message) {
        if (message != null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
