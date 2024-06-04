package fr.azrotho.twitchlink.customrewards.rewards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.azrotho.twitchlink.customrewards.CustomReward;

public class ShuffleInventory extends CustomReward {
    public ShuffleInventory() {
        super("Mélanger l'inventaire");
    }

    @Override
    public void execute(Player player, String redeemerName) {
        mixInventory(player);
    }

    public static void mixInventory(Player player) {
        Inventory inventory = player.getInventory();
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            items.add(inventory.getItem(i));
        }
        Collections.shuffle(items);
        player.getInventory().clear();
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, items.get(i));
        }

    }
}

