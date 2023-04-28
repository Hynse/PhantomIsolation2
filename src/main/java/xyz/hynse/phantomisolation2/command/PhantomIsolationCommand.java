package xyz.hynse.phantomisolation2.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.phantomisolation2.PhantomIsolation2;
import xyz.hynse.phantomisolation2.util.FlatFileDatabaseUtil;

public class PhantomIsolationCommand implements CommandExecutor {
    private final PhantomIsolation2 plugin;

    public PhantomIsolationCommand(PhantomIsolation2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("phantomisolation")) {
            if (args.length == 0) {
                sender.sendMessage("Usage: /phantomisolation [check|disable|enable]");
                return true;
            }
            if (args[0].equalsIgnoreCase("check")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageStatus + FlatFileDatabaseUtil.getPlayerIsolationStatus(player)));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageNotPlayer));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("disable")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    FlatFileDatabaseUtil.setPlayerIsolationStatus(player, false);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageDisable));;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageNotPlayer));;
                }
                return true;
            } else if (args[0].equalsIgnoreCase("enable")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    FlatFileDatabaseUtil.setPlayerIsolationStatus(player, true);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageEnabled));;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageNotPlayer));;
                }
                return true;
            }
        }
        return false;
    }
}
