package xyz.hynse.phantomisolation2.listener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class PhantomIsolationTabCompleterListener implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("phantomisolation") || command.getName().equalsIgnoreCase("pli")) {
            if (args.length == 1) {
                List<String> subcommands = new ArrayList<>();
                subcommands.add("check");
                subcommands.add("disable");
                subcommands.add("enable");
                subcommands.add("on");
                subcommands.add("off");
                subcommands.add("status");

                return subcommands;
            }
        }
        return null;
    }
}