package xyz.hynse.phantomisolation2;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.phantomisolation2.command.PhantomIsolationCommand;
import xyz.hynse.phantomisolation2.command.ReloadCommand;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationListener;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationTabCompleterListener;
import xyz.hynse.phantomisolation2.util.DatabaseUtil;
import xyz.hynse.phantomisolation2.util.MiscUtil;
public class PhantomIsolation2 extends JavaPlugin {
    public static PhantomIsolation2 instance;
    public static PhantomIsolationListener PhantomIsolationListener;
    public static MiscUtil MiscUtil;
    public static DatabaseUtil databaseUtil;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        register();
        MiscUtil.reload();
        MiscUtil.initDatabase();
        PhantomIsolationListener.start();
    }

    public void register() {
        instance.getCommand("phantomisolation").setExecutor(new PhantomIsolationCommand());
        instance.getCommand("phantomisolationreload").setExecutor(new ReloadCommand());
        instance.getCommand("phantomisolation").setTabCompleter(new PhantomIsolationTabCompleterListener());
        instance.getServer().getPluginManager().registerEvents(new PhantomIsolationListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new MiscUtil(), instance);
    }
}
