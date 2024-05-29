package fr.azrotho.twitchlink.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.azrotho.twitchlink.TwitchLink;

public class ConfigReaderUtil {


    public boolean checkIfConfigExist() {
        File folder = TwitchLink.getInstance().getDataFolder();
        File configFile = new File(folder, "config.yml");
        return configFile.exists();
    }


    public void createConfigFile() {
        File folder = TwitchLink.getInstance().getDataFolder();
        File configFile = new File(folder, "config.yml");

        if(!folder.exists()) folder.mkdir();

        if (!configFile.exists()) {
            try (InputStream inputStream = TwitchLink.getInstance().getResource("config.yml");
                 OutputStream outputStream = new FileOutputStream(configFile)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                TwitchLink.getInstance().getLogger().info("config.yml has been created");
            } catch (IOException e) {
                TwitchLink.getInstance().getLogger().severe("An error occurred while creating config.yml");
            }
        }
    }

    public void loadConfig() throws IOException {
        if(!checkIfConfigExist()) createConfigFile();
        TwitchLink.getInstance().getLogger().info("Config loaded");
    }

    public YamlConfiguration getConfig() {
        createConfigFile();
        return YamlConfiguration.loadConfiguration(new File(TwitchLink.getInstance().getDataFolder(), "config.yml"));
    }
}
