package com.github.poxiton;

import com.github.poxiton.utilities.ItemsData;
import com.github.poxiton.utilities.ItemsDataType;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;

public class StatsManager {

    private Plugin plugin;

    public StatsManager(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Life Steal Stat method to heal player
     *
     * @param player Represents a player
     * @param damage Given damage for entity
     * @param item   Item that deals damage
     */
    public void healPlayer(Player player, ItemStack item, double damage) {
        PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();

        float lifeSteal = itemData.get(new NamespacedKey(plugin, "itemStats"), new ItemsDataType()).lifesteal;
        float playerMaxHealth = (float) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        float playerNewHealth = (float) (damage * lifeSteal + player.getHealth());

        if (playerNewHealth >= playerMaxHealth)
            playerNewHealth = playerMaxHealth;

        player.setHealth(playerNewHealth);
    }

    public void setItemStat(ItemStack item, ItemsData itemsData) {
        ItemMeta itemMeta = item.getItemMeta();
        ItemsData itemData = itemsData;

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "itemStats"), new ItemsDataType(),
                itemData);

        item.setItemMeta(itemMeta);
    }
}
