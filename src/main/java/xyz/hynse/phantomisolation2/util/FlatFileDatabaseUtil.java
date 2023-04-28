package xyz.hynse.phantomisolation2.util;

import org.bukkit.entity.Player;
import xyz.hynse.phantomisolation2.PhantomIsolation2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FlatFileDatabaseUtil {
    private static final Set<UUID> isolatedPlayers = new HashSet<>();

    public static boolean getPlayerIsolationStatus(Player player) {
        return isolatedPlayers.contains(player.getUniqueId());
    }

    public static void setPlayerIsolationStatus(Player player, boolean isEnabled) {
        if (isEnabled) {
            isolatedPlayers.add(player.getUniqueId());
        } else {
            isolatedPlayers.remove(player.getUniqueId());
        }
        saveData();
    }

    public static void loadData() {
        Path dataPath = getDataFilePath();
        if (Files.exists(dataPath)) {
            try {
                Files.readAllLines(dataPath).forEach(line -> isolatedPlayers.add(UUID.fromString(line)));
            } catch (IOException e) {
                PhantomIsolation2.instance.getLogger().severe("Failed to load data from file: " + e.getMessage());
            }
        }
    }

    private static void saveData() {
        StringBuilder dataBuilder = new StringBuilder();
        isolatedPlayers.forEach(uuid -> dataBuilder.append(uuid.toString()).append('\n'));

        try {
            Files.write(getDataFilePath(), dataBuilder.toString().getBytes());
        } catch (IOException e) {
            PhantomIsolation2.instance.getLogger().severe("Failed to save data to file: " + e.getMessage());
        }
    }

    private static Path getDataFilePath() {
        File dataFolder = PhantomIsolation2.instance.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        return Paths.get(dataFolder.getPath(), "isolated_players.txt");
    }
}
