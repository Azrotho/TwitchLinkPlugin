package fr.azrotho.twitchlink.runnable;

import org.bukkit.scheduler.BukkitRunnable;

import fr.azrotho.twitchlink.TwitchLink;
import fr.azrotho.twitchlink.object.CustomRewardObject;

import java.util.ArrayList;
import java.util.List;

public class CustomRewardsRunnable extends BukkitRunnable {
    public static List<CustomRewardObject> rewardsToRun = new ArrayList<CustomRewardObject>();

    @Override
    public void run() {
        if(rewardsToRun.size() > 0) {
            CustomRewardObject reward = rewardsToRun.get(0);
            if(TwitchLink.customRewards.rewardExist(reward.getTitle())) {
                TwitchLink.customRewards.execute(reward.getTitle(), reward.getPlayer(), reward.getRedeemerName());
            }
            rewardsToRun.remove(0);
        }
    }
}
