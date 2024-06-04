package fr.azrotho.twitchlink.customrewards.rewards;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.azrotho.twitchlink.customrewards.CustomReward;

public class ReplaceGappWithASuspiciousStew extends CustomReward {

    public ReplaceGappWithASuspiciousStew() {
        super("Remplacer une gapp par une soupe qui heal");
    }

    @Override
    public void execute(Player player, String redeemerName) {
        replaceGappWithASuspiciousStew(player);
    }

    public void replaceGappWithASuspiciousStew(Player player) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType().equals(Material.GOLDEN_APPLE)) {
                ItemStack suspiciousStew = new ItemStack(Material.SUSPICIOUS_STEW);
                SuspiciousStewMeta meta = (SuspiciousStewMeta) suspiciousStew.getItemMeta();
                meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1), true);
                suspiciousStew.setItemMeta(meta);
                inventory.addItem(suspiciousStew);
                inventory.removeItem(new ItemStack(Material.GOLDEN_APPLE, 1));
                break;
            }
        }
    }
    
}
