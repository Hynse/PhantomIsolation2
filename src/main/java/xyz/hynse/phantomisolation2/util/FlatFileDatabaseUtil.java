package xyz.hynse.phantomisolation2.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.hynse.phantomisolation2.PhantomIsolation2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FlatFileDatabaseUtil implements DatabaseUtil {
    private static final Map<UUID, String> isolatedPlayers = new HashMap<>();

    public boolean getPlayerIsolationStatus(Player player) {
        return isolatedPlayers.containsKey(player.getUniqueId());
    }

    public void setPlayerIsolationStatus(Player player, boolean isEnabled) {
        if (isEnabled) {
            isolatedPlayers.put(player.getUniqueId(), "enabled");
        } else {
            isolatedPlayers.remove(player.getUniqueId());
        }
        saveData();
    }

    @Override
    public void loadData() {
        Path dataPath = getDataFilePath();
        if (Files.exists(dataPath)) {
            try {
                Gson gson = new Gson();
                String data = Files.readString(dataPath, StandardCharsets.UTF_8);
                Map<String, Map<String, String>> dataMap = gson.fromJson(data, Map.class);
                for (Map.Entry<String, Map<String, String>> entry : dataMap.entrySet()) {
                    UUID uuid = UUID.fromString(entry.getKey());
                    String status = entry.getValue().get("status");
                    isolatedPlayers.put(uuid, status);
                }
            } catch (IOException e) {
                PhantomIsolation2.instance.getLogger().info(ChatColor.translateAlternateColorCodes('&', MiscUtil.phantomisolationmessageDatabaseFailLoad + e.getMessage()));
            }
        }
    }

    private static void saveData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Map<String, String>> dataMap = new HashMap<>();
        isolatedPlayers.forEach((uuid, status) -> {
            Map<String, String> playerData = new HashMap<>();
            playerData.put("username", Bukkit.getOfflinePlayer(uuid).getName());
            playerData.put("status", status);
            dataMap.put(uuid.toString(), playerData);
        });
        String jsonData = gson.toJson(dataMap);

        try {
            Files.writeString(getDataFilePath(), jsonData);
        } catch (IOException e) {
            PhantomIsolation2.instance.getLogger().info(ChatColor.translateAlternateColorCodes('&', MiscUtil.phantomisolationmessageDatabaseFailSave + e.getMessage()));
        }
    }

    private static Path getDataFilePath() {
        File dataFolder = PhantomIsolation2.instance.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        return Paths.get(dataFolder.getPath(), "isolated_players.json");
    }
}
