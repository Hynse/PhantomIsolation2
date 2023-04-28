package xyz.hynse.phantomisolation2.database;

import java.sql.*;

import org.bukkit.entity.Player;

import xyz.hynse.phantomisolation2.PhantomIsolation2;

public class DatabaseManager {
    private Connection connection;
    private final String databaseName;

    public DatabaseManager(String databaseName) {
        this.databaseName = databaseName;
        try {
            openConnection();
        } catch (SQLException | ClassNotFoundException e) {
            PhantomIsolation2.instance.getLogger().warning("Failed to open database connection: " + e.getMessage());
        }
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        String createTable = "CREATE TABLE IF NOT EXISTS player_status ("
                + "player_uuid TEXT PRIMARY KEY,"
                + "isolation_enabled INTEGER NOT NULL);";
        PreparedStatement stmt = connection.prepareStatement(createTable);
        stmt.executeUpdate();
    }

    public void closeConnection() throws SQLException {
        if (connection == null) {
            return;
        }
        connection.close();
    }

    public boolean getPlayerIsolationStatus(Player player) {
        boolean isEnabled = true;
        String query = "SELECT isolation_enabled FROM player_status WHERE player_uuid = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, player.getUniqueId().toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isEnabled = rs.getBoolean("isolation_enabled");
            } else {
                setPlayerIsolationStatus(player, true);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            PhantomIsolation2.instance.getLogger().warning("Failed to get player isolation status from database: " + e.getMessage());
        }
        return isEnabled;
    }

    public void setPlayerIsolationStatus(Player player, boolean isEnabled) {
        String query = "REPLACE INTO player_status (player_uuid, isolation_enabled) VALUES (?, ?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, player.getUniqueId().toString());
            stmt.setBoolean(2, isEnabled);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            PhantomIsolation2.instance.getLogger().warning("Failed to set player isolation status to database: " + e.getMessage());
        }
    }

    public void loadPlayerIsolationStatus(Player player) {
        PhantomIsolation2.instance.getDatabaseManager().setPlayerIsolationStatus(player, getPlayerIsolationStatus(player));
    }

    public void savePlayerIsolationStatus(Player player) {
        PhantomIsolation2.instance.getDatabaseManager().setPlayerIsolationStatus(player, PhantomIsolation2.instance.getDatabaseManager().getPlayerIsolationStatus(player));
    }

}
