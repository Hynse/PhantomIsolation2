package xyz.hynse.phantomisolation2.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.phantomisolation2.PhantomIsolation2;
import xyz.hynse.phantomisolation2.util.DatabaseUtil;
import xyz.hynse.phantomisolation2.util.MiscUtil;

public class PhantomIsolationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("phantomisolation.use")) {
            MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageNoPermission);
            return true;
        }

        DatabaseUtil databaseUtil = PhantomIsolation2.databaseUtil;

        if (command.getName().equalsIgnoreCase("phantomisolation")) {
            if (args.length == 0) {
                MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageUsage);
                return true;
            }

            if (!(sender instanceof Player)) {
                MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageNotPlayer);
                return true;
            }

            Player player = (Player) sender;

            switch (args[0].toLowerCase()) {
                case "check", "status" -> {
                    boolean status = databaseUtil.getPlayerIsolationStatus(player);
                    String statusText = status ? MiscUtil.phantomisolationMessageStatusEnabled : MiscUtil.phantomisolationMessageStatusDisabled;
                    String message = MiscUtil.phantomisolationMessageStatus.replace("%status%", statusText);
                    MiscUtil.sendMessage(sender, message);
                }
                case "disable", "off" -> {
                    databaseUtil.setPlayerIsolationStatus(player, false);
                    MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageDisable);
                }
                case "enable", "on" -> {
                    databaseUtil.setPlayerIsolationStatus(player, true);
                    MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageEnabled);
                }
                default -> MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageUsage);
            }
            return true;
        }
        return false;
    }

}
