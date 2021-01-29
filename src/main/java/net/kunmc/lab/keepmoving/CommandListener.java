package net.kunmc.lab.keepmoving;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandListener implements CommandExecutor, TabCompleter {
    private KeepMoving instance = null;

    CommandListener() {
    }

    CommandListener(KeepMoving instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return false;
        try {
            Integer delay = Integer.parseInt(args[1]);
            if (args[0].equals("delay")) {
                this.instance.delay = delay;
                sender.sendMessage("delay is set to " + delay.toString());
            } else if (args[0].equals("liquidDelay")) {
                this.instance.liquidDelay = delay;
                sender.sendMessage("liquidDelay is set to " + delay.toString());
            } else {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>();
        if (args.length == 1) {
            l.add("delay");
            l.add("liquidDelay");
        } else if (args.length == 2) {
        }
        return l;
    }
}
