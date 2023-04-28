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
                sendMessage(sender, PhantomIsolation2.phantomisolationMessageUsage);
                return true;
            }

            if (!(sender instanceof Player)) {
                sendMessage(sender, PhantomIsolation2.phantomisolationMessageNotPlayer);
                return true;
            }

            Player player = (Player) sender;

            switch (args[0].toLowerCase()) {
                case "check":
                case "status":
                    boolean status = databaseUtil.getPlayerIsolationStatus(player);
                    String statusText = status ? PhantomIsolation2.phantomisolationMessageStatusEnabled : PhantomIsolation2.phantomisolationMessageStatusDisabled;
                    String message = PhantomIsolation2.phantomisolationMessageStatus.replace("%status%", statusText);
                    sendMessage(sender, message);
                    break;

                case "disable":
                case "off":
                    databaseUtil.setPlayerIsolationStatus(player, false);
                    sendMessage(sender, PhantomIsolation2.phantomisolationMessageDisable);
                    break;

                case "enable":
                case "on":
                    databaseUtil.setPlayerIsolationStatus(player, true);
                    sendMessage(sender, PhantomIsolation2.phantomisolationMessageEnabled);
                    break;

                default:
                    sendMessage(sender, PhantomIsolation2.phantomisolationMessageUsage);
            }
            return true;
        }
        return false;
    }

    private void sendMessage(CommandSender sender, String message) {
        if (message != null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
