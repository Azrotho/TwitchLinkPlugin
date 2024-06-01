package fr.azrotho.twitchlink.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import fr.azrotho.twitchlink.TwitchLink;
import fr.azrotho.twitchlink.Runnable.CommandRunnable;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class OnChannelPoint {
    public static void onChannelPoint(RewardRedeemedEvent event) {
        String title = event.getRedemption().getReward().getTitle();
        String username = event.getRedemption().getUser().getDisplayName();
        for(Player player : Bukkit.getOnlinePlayers()) {
            String command = ChannelPointsRewardUtils.cacheReward.getString(title.replace(" ", "_"));
            if(command == null) return;
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(TwitchLink.tag + "§5§l" + username + " §fa réclamé §5§l" + title + "§f !"));
            command = command.replace("%player%", player.getName());
            command = command.replace("%reward%", title);
            command = command.replace("%redeemerName%", username);
            CommandRunnable.commandsToRun.add(command);
        }
    }
}
