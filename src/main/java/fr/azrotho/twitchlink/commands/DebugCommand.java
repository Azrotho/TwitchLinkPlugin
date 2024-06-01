package fr.azrotho.twitchlink.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.azrotho.twitchlink.utils.ChannelPointsRewardUtils;

public class DebugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        switch(args[0]) {
            case "reload":
                ChannelPointsRewardUtils.cacheReward = new ChannelPointsRewardUtils().getConfig();
                break;
            case "test":
                // Test the config
                break;
            default:
                // Display help
                break;
        }
        return true;
    }
    
}
