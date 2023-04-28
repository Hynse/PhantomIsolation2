package xyz.hynse.phantomisolation2.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.hynse.phantomisolation2.PhantomIsolation2;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("phantomisolation.reload")) return true;
        try {
            PhantomIsolation2.instance.reload();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageReloadConfig));
        } catch (Exception e) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PhantomIsolation2.phantomisolationMessageReloadConfigError));
            e.printStackTrace();
        }
        return true;
    }
}
