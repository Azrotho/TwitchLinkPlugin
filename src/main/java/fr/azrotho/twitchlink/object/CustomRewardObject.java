package fr.azrotho.twitchlink.object;

import org.bukkit.entity.Player;

public class CustomRewardObject {
    private String title;
    private String redeemerName;
    private Player player;

    public CustomRewardObject(String title, String redeemerName, Player player) {
        this.title = title;
        this.redeemerName = redeemerName;
        this.player = player;
    }

    public String getTitle() {
        return title;
    }

    public String getRedeemerName() {
        return redeemerName;
    }

    public Player getPlayer() {
        return player;
    }
}
