package fr.azrotho.twitchlink.customrewards;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import fr.azrotho.twitchlink.customrewards.rewards.ChunkMiner;

public class CustomRewards {
    private final ArrayList<CustomReward> rewards = new ArrayList<>();

    public CustomRewards() {
        rewards.add(new ChunkMiner());
    }

    public Boolean rewardExist(String title) {
        for(CustomReward reward : rewards) {
            if(reward.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void execute(String title, Player player, String redeemerName) {
        for(CustomReward customReward : rewards) {
            if(customReward.getTitle().equals(title)) {
                customReward.execute(player, redeemerName);
            }
        }
    }
}
