package xyz.hynse.phantomisolation2.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.hynse.phantomisolation2.PhantomIsolation2;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("phantomisolation.reload")) {
            sendMessage(sender, PhantomIsolation2.phantomisolationMessageNoPermission);
            return true;
        }

        try {
            PhantomIsolation2.instance.reload();
            sendMessage(sender, PhantomIsolation2.phantomisolationMessageReloadConfig);
        } catch (Exception e) {
            sendMessage(sender, PhantomIsolation2.phantomisolationMessageReloadConfigError);
            e.printStackTrace();
        }

        return true;
    }

    private void sendMessage(CommandSender sender, String message) {
        if (message != null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
