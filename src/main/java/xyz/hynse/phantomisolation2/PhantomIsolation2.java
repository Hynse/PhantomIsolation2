package xyz.hynse.phantomisolation2;

import org.bukkit.plugin.java.JavaPlugin;
import space.arim.morepaperlib.MorePaperLib;
import xyz.hynse.phantomisolation2.command.PhantomIsolationCommand;
import xyz.hynse.phantomisolation2.database.DatabaseManager;
import xyz.hynse.phantomisolation2.listener.PlayerJoinQuitListener;
import xyz.hynse.phantomisolation2.scheduler.PhantomIsolationScheduler;

public class PhantomIsolation2 extends JavaPlugin {
    public static PhantomIsolation2 instance;
    private MorePaperLib morePaperLib;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        instance = this;
        this.morePaperLib = new MorePaperLib(this);

        register();
    }

    private void register() {
        getCommand("phantomisolation").setExecutor(new PhantomIsolationCommand(this));
        PhantomIsolationScheduler scheduler = new PhantomIsolationScheduler(getMorePaperLib());
        scheduler.start();
        this.databaseManager = new DatabaseManager("database.db");
        getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(databaseManager), this);
    }

    public MorePaperLib getMorePaperLib() {
        return morePaperLib;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
