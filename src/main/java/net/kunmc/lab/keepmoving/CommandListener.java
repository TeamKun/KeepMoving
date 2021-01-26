package net.kunmc.lab.keepmoving;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {
    private final KeepMoving instance;

    CommandListener(KeepMoving instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) return false;
        try {
            Integer tick = Integer.parseInt(args[0]);
            this.instance.delay = tick;
            sender.sendMessage("delay-time is set to " + tick.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
