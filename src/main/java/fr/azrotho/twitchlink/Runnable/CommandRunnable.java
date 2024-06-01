package fr.azrotho.twitchlink.Runnable;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;


public class CommandRunnable extends BukkitRunnable {
    public static List<String> commandsToRun = new ArrayList<String>();

    @Override
    public void run() {
        if (commandsToRun.size() > 0) {
            String command = commandsToRun.get(0);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            commandsToRun.remove(0);
        }
    }
    
}
