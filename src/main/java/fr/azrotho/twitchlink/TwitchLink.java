package fr.azrotho.twitchlink;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;

public class TwitchLink extends JavaPlugin {
    private static TwitchClient twitchClient;

    @Override
    public void onEnable() {
        TwitchClient twitchClientinit = TwitchClientBuilder.builder()
            .withEnablePubSub(true)
            .build();
        twitchClient = twitchClientinit;

    }

    public static TwitchClient getTwitchClient() {
        return twitchClient;
    }
}
