package fr.azrotho.twitchlink.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class OnChannelPoint {
    public static void onChannelPoint(RewardRedeemedEvent event) {
        String title = event.getRedemption().getReward().getTitle();
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§e§l" + event.getRedemption().getUser().getDisplayName() + " §aa réclamé §c§l" + title));
        }
    }
}
