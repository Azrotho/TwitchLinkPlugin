package fr.azrotho.twitchlink;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;

import fr.azrotho.twitchlink.utils.ConfigReaderUtil;
import fr.azrotho.twitchlink.utils.OnChannelPoint;

public class TwitchLink extends JavaPlugin {
    private static TwitchClient twitchClient;
    private static TwitchLink instance;

    @Override
    public void onEnable() {
        instance = this;
        YamlConfiguration config = new ConfigReaderUtil().getConfig();
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
    }

    public static TwitchClient getTwitchClient() {
        return twitchClient;
    }

    public static TwitchLink getInstance() {
        return instance;
    }
}
