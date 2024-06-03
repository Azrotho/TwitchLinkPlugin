package fr.azrotho.twitchlink.event;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import fr.azrotho.twitchlink.TwitchLink;
import fr.azrotho.twitchlink.object.CustomRewardObject;
import fr.azrotho.twitchlink.runnable.CommandsRunnable;
import fr.azrotho.twitchlink.runnable.CustomRewardsRunnable;
import fr.azrotho.twitchlink.utils.ChannelPointsRewardUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class OnChannelPoint {
    public static void onChannelPoint(RewardRedeemedEvent event) {
        String title = event.getRedemption().getReward().getTitle();
        String username = event.getRedemption().getUser().getDisplayName();
        for(Player player : Bukkit.getOnlinePlayers()) {
            String command = ChannelPointsRewardUtils.cacheReward.getString(title.replace(" ", "_"));
            if(command == null && !TwitchLink.customRewards.rewardExist(title)) return;
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(TwitchLink.tag + "§5§l" + username + " §fa réclamé §5§l" + title + "§f !"));
            
            if(TwitchLink.customRewards.rewardExist(title)) {
                CustomRewardObject customRewardObject = new CustomRewardObject(title, username, player);
                CustomRewardsRunnable.rewardsToRun.add(customRewardObject);
            } else {
                command = command.replace("%player%", player.getName());
                command = command.replace("%reward%", title);
                command = command.replace("%redeemerName%", username);
                CommandsRunnable.commandsToRun.add(command);
            }
            player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_PLACE, 1f, 1f);
        }
    }
}
