package xyz.hynse.phantomisolation2;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.phantomisolation2.command.PhantomIsolationCommand;
import xyz.hynse.phantomisolation2.command.ReloadCommand;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationListener;
import xyz.hynse.phantomisolation2.listener.PhantomIsolationTabCompleterListener;
import xyz.hynse.phantomisolation2.util.DatabaseUtil;
import xyz.hynse.phantomisolation2.util.FlatFileDatabaseUtil;
import xyz.hynse.phantomisolation2.util.MiscUtil;
public class PhantomIsolation2 extends JavaPlugin implements Listener {
    public static PhantomIsolation2 instance;
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
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PhantomIsolationListener.start();
    }
    public void register() {
        getCommand("phantomisolation").setExecutor(new PhantomIsolationCommand());
        getCommand("phantomisolationreload").setExecutor(new ReloadCommand());
        getCommand("phantomisolation").setTabCompleter(new PhantomIsolationTabCompleterListener());
        getServer().getPluginManager().registerEvents(new PhantomIsolationListener(), this);
        getServer().getPluginManager().registerEvents(new MiscUtil(), this);}
}
