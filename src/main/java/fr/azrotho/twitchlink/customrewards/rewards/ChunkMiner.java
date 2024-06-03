package fr.azrotho.twitchlink.customrewards.rewards;

import org.bukkit.entity.Player;

import fr.azrotho.twitchlink.customrewards.CustomReward;

public class ChunkMiner extends CustomReward {

    public ChunkMiner() {
        super("Miner un Chunk");
    }

    @Override
    public void execute(Player player, String redeemerName) {
        player.sendMessage("Vous avez miné un chunk !");
    }
    
}
