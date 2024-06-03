package fr.azrotho.twitchlink;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;

import fr.azrotho.twitchlink.commands.DebugCommand;
import fr.azrotho.twitchlink.customrewards.CustomRewards;
import fr.azrotho.twitchlink.utils.ChannelPointsRewardUtils;
import fr.azrotho.twitchlink.utils.ConfigReaderUtils;
import fr.azrotho.twitchlink.event.OnChannelPoint;
import fr.azrotho.twitchlink.runnable.CustomRewardsRunnable;
import fr.azrotho.twitchlink.runnable.CommandsRunnable;

public class TwitchLink extends JavaPlugin {
    private static TwitchClient twitchClient;
    private static TwitchLink instance;
    public static String tag = "§f§l[§5TwitchLink§f§l] §f";
    public static CustomRewards customRewards = new CustomRewards();

    @Override
    public void onEnable() {
        instance = this;
        YamlConfiguration config = new ConfigReaderUtils().getConfig();
        ChannelPointsRewardUtils.cacheReward = new ChannelPointsRewardUtils().getConfig();
        twitchClient = TwitchClientBuilder.builder()
            .withClientId(config.getString("client_id"))
            .withClientSecret(config.getString("client_secret"))
            .withDefaultAuthToken(new OAuth2Credential("twitch", config.getString("oauth2")))
            .withEnablePubSub(true)
            .withEnableChat(true)
            .build();

        OAuth2Credential oAuth2Credential = new OAuth2Credential("twitch", config.getString("oauth2"));
        twitchClient.getPubSub().listenForChannelPointsRedemptionEvents(oAuth2Credential, config.getString("channel_id"));
        twitchClient.getEventManager().onEvent(RewardRedeemedEvent.class, OnChannelPoint::onChannelPoint);

        getCommand("debug").setExecutor(new DebugCommand());

        CommandsRunnable commandRunnable = new CommandsRunnable();
        commandRunnable.runTaskTimer(this, 0, 0);

        CustomRewardsRunnable customRewardsRunnable = new CustomRewardsRunnable();
        customRewardsRunnable.runTaskTimer(this, 0, 0);
    }

    public static TwitchClient getTwitchClient() {
        return twitchClient;
    }

    public static TwitchLink getInstance() {
        return instance;
    }
}
