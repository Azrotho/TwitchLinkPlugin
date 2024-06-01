package fr.azrotho.twitchlink.utils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import fr.azrotho.twitchlink.TwitchLink;

public class ChannelPointsRewardUtils {
    public static YamlConfiguration cacheReward;

    public boolean checkIfConfigExist() {
        File folder = TwitchLink.getInstance().getDataFolder();
        File configFile = new File(folder, "channelpoints.yml");
        return configFile.exists();
    }

    public void createConfigFile() {
        File folder = TwitchLink.getInstance().getDataFolder();
        File configFile = new File(folder, "channelpoints.yml");

        if(!folder.exists()) folder.mkdir();

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                TwitchLink.getInstance().getLogger().info("channelpoints.yml has been created");
            } catch (Exception e) {
                TwitchLink.getInstance().getLogger().severe("An error occurred while creating channelpoints.yml");
            }
        }
    }

    public void loadConfig() {
        if(!checkIfConfigExist()) createConfigFile();
        TwitchLink.getInstance().getLogger().info("Channel Points Reward loaded");
    }

    public YamlConfiguration getConfig() {
        createConfigFile();
        return YamlConfiguration.loadConfiguration(new File(TwitchLink.getInstance().getDataFolder(), "channelpoints.yml"));
    }

    public void reloadConfig() {
        cacheReward = getConfig();
    }


}
