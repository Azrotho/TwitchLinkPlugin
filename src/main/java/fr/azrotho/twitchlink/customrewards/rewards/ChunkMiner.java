package fr.azrotho.twitchlink.customrewards.rewards;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.azrotho.twitchlink.customrewards.CustomReward;

public class ChunkMiner extends CustomReward {

    public ChunkMiner() {
        super("Miner un Chunk");
    }

    @Override
    public void execute(Player player, String redeemerName) {
        mineChunk(player.getLocation().getChunk());
    }

    public void mineChunk(Chunk chunk) {
        int minimalY = 0;
        int maximalY = 256;
        if(chunk.getWorld().getName().equals("world")) {
            minimalY = -64;
            maximalY = 320;
        }
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                for(int y = minimalY; y < maximalY; y++) {
                    if(!chunk.getBlock(x, y, z).getType().equals(Material.WATER) && !chunk.getBlock(x, y, z).getType().equals(Material.LAVA) && !chunk.getBlock(x, y, z).getType().equals(Material.BEDROCK)) {
                        chunk.getBlock(x, y, z).setType(Material.AIR);
                    }
                }
            }
        }
    }
    
}
