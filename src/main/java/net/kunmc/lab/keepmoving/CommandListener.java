package net.kunmc.lab.keepmoving;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandListener implements CommandExecutor, TabCompleter {
    private KeepMoving instance = null;

    CommandListener(){}

    CommandListener(KeepMoving instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) return false;
        try {
            Integer delay = Integer.parseInt(args[0]);
            this.instance.delay = delay;
            sender.sendMessage("delay is set to " + delay.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command, String alias, String[] args) {
        return new ArrayList<String>();
    }
}
