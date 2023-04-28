package xyz.hynse.phantomisolation2.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.phantomisolation2.PhantomIsolation2;
import xyz.hynse.phantomisolation2.util.DatabaseUtil;

public class PhantomIsolationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        DatabaseUtil databaseUtil = PhantomIsolation2.databaseUtil;
        if (command.getName().equalsIgnoreCase("phantomisolation")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageUsage));
                return true;
            }
            if (args[0].equalsIgnoreCase("check") || args[0].equalsIgnoreCase("status")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    boolean status = databaseUtil.getPlayerIsolationStatus(player);
                    String statusText = status ? PhantomIsolation2.phantomisolationMessageStatusEnabled : PhantomIsolation2.phantomisolationMessageStatusDisabled;
                    String message = PhantomIsolation2.phantomisolationMessageStatus.replace("%status%", statusText);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageNotPlayer));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    databaseUtil.setPlayerIsolationStatus(player, false);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageDisable));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageNotPlayer));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    databaseUtil.setPlayerIsolationStatus(player, true);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageEnabled));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageNotPlayer));
                }
                return true;
            }
        }
        return false;
    }
}
