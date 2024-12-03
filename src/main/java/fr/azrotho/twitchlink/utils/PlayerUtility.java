package fr.azrotho.twitchlink.utils;

import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import fr.azrotho.twitchlink.TwitchLink;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerUtility {
    public static Player getPlayerFromChannelPointReward(RewardRedeemedEvent event) {
        if(event.getRedemption().getChannelId().equals(TwitchLink.getInstance().getConfig().getString("azrotho-channel_id"))) {
            return Bukkit.getPlayer("Azrotho");
        }
        if(event.getRedemption().getChannelId().equals(TwitchLink.getInstance().getConfig().getString("nissoku-channel_id"))) {
            return Bukkit.getPlayer("Nissoku");
        }
        return null;
    }
}
