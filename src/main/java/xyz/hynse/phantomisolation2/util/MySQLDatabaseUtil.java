package xyz.hynse.phantomisolation2.util;

import org.bukkit.entity.Player;
import xyz.hynse.phantomisolation2.PhantomIsolation2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDatabaseUtil implements DatabaseUtil {
    private final Connection connection;

    public MySQLDatabaseUtil() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + PhantomIsolation2.address + "/" + PhantomIsolation2.database + "?useSSL=false", PhantomIsolation2.user, PhantomIsolation2.password);
        createTable();
    }

    private void createTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS phantom_isolation_players (uuid VARCHAR(36) PRIMARY KEY);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        }
    }

    @Override
    public boolean getPlayerIsolationStatus(Player player) {
        // Implement this method for MySQL.
        return false;
    }

    @Override
    public void setPlayerIsolationStatus(Player player, boolean isEnabled) {
        // Implement this method for MySQL.
    }

    @Override
    public void loadData() {
        // Implement this method for MySQL.
    }
}
