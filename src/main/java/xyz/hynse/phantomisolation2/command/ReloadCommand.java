package xyz.hynse.phantomisolation2.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.hynse.phantomisolation2.util.MiscUtil;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("phantomisolation.reload")) {
            MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageNoPermission);
            return true;
        }

        try {
            MiscUtil.reload();
            MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageReloadConfig);
        } catch (Exception e) {
            MiscUtil.sendMessage(sender, MiscUtil.phantomisolationMessageReloadConfigError);
            e.printStackTrace();
        }

        return true;
    }

}
