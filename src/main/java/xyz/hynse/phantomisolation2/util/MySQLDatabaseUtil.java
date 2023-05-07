package xyz.hynse.phantomisolation2.util;

import org.bukkit.entity.Player;
import xyz.hynse.phantomisolation2.PhantomIsolation2;

import java.sql.*;
import java.util.UUID;

public class MySQLDatabaseUtil implements DatabaseUtil {
    private final Connection connection;

    public MySQLDatabaseUtil() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + MiscUtil.address + "/" + MiscUtil.database + "?useSSL=false", MiscUtil.user, MiscUtil.password);
        createTable();
    }

    private void createTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS phantom_isolation_players (username VARCHAR(16), uuid VARCHAR(36) PRIMARY KEY, status BOOLEAN);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        }
    }

    @Override
    public boolean getPlayerIsolationStatus(Player player) {
        String query = "SELECT * FROM phantom_isolation_players WHERE uuid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, player.getUniqueId().toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setPlayerIsolationStatus(Player player, boolean isEnabled) {
        String query = "INSERT INTO phantom_isolation_players (username, uuid, status) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE status = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.setBoolean(3, isEnabled);
            preparedStatement.setBoolean(4, isEnabled);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        String query = "SELECT * FROM phantom_isolation_players";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    boolean status = resultSet.getBoolean("status");
                    Player player = PhantomIsolation2.instance.getServer().getPlayer(UUID.fromString(uuid));
                    if (player != null && player.isOnline()) {
                        PhantomIsolation2.databaseUtil.setPlayerIsolationStatus(player, status);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
