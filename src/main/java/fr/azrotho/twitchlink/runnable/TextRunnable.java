package fr.azrotho.twitchlink.runnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.azrotho.twitchlink.TwitchLink;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class TextRunnable extends BukkitRunnable {
    public static int tickWithoutUpdate = 0;
    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(TwitchLink.isEnable) {
                if(tickWithoutUpdate == 0) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§5§lPoints de chaîne dépensés: §f" + ((formatLong(TwitchLink.numberChannelPointsSpent)).replace(" ", "."))));
                } else {
                    tickWithoutUpdate--;
                }  
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lLes récompenses de points ne sont pas actif."));
            }
        }
    }

    public static String formatLong(long number) {
        if (number == 0) {
          return "0";
        }
      
        String formattedNumber = String.format("%,.0f", number / 1.0);
        return formattedNumber;
      }
}
