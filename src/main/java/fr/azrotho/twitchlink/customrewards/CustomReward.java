package fr.azrotho.twitchlink.customrewards;

import org.bukkit.entity.Player;

public class CustomReward {
    private String title;

    public CustomReward(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void execute(Player player, String redeemerName) {}
}
