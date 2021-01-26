package net.kunmc.lab.keepmoving;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public final class KeepMoving extends JavaPlugin implements Listener {
    private final HashMap<String, BukkitTask> tasks = new HashMap<>();
    public int delay = 4;
    private int liquidDelay = 14;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("keepmoving").setExecutor(new CommandListener(this));
        getServer().broadcastMessage("KeepMove started");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Double distance = e.getFrom().distance(e.getTo());
        if (distance == 0.0F) return;
        if ((0.07840F < distance && distance < 0.07850F)) return;
        if ((0.0990F < distance && distance < 0.1000F)) return;
        if ((0.02150F < distance && distance < 0.02160F)) return;

        String name = e.getPlayer().getName();
        if (this.tasks.get(name) != null) this.tasks.get(name).cancel();
        if (e.getPlayer().getLocation().getBlock().isLiquid()) {
            this.tasks.put(name, new kill(e.getPlayer()).runTaskLater(this, liquidDelay));
        } else {
            this.tasks.put(name, new kill(e.getPlayer()).runTaskLater(this, delay));
        }
    }

    @EventHandler
    public void onJump(PlayerJumpEvent e) {
        String name = e.getPlayer().getName();
        if (this.tasks.get(name) != null) this.tasks.get(name).cancel();
        this.tasks.put(name, new kill(e.getPlayer()).runTaskLater(this, delay));
    }

    class kill extends BukkitRunnable {
        private final Player p;

        kill(Player p) {
            this.p = p;
        }

        @Override
        public void run() {
            this.p.damage(100);
        }
    }
}
