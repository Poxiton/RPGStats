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
   * @param player Player to be healed
   * @param damage Given damage to entity
   * @param item   Item which was used
   */
  public void lifeStealHeal(Player player, ItemStack item, double damage) {
    PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();

    float lifeSteal = itemData.get(new NamespacedKey(plugin, "itemStats"), new ItemsDataType()).lifesteal;
    float playerMaxHealth = (float) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    float playerNewHealth = (float) (damage * lifeSteal + player.getHealth());

    if (playerNewHealth >= playerMaxHealth)
      playerNewHealth = playerMaxHealth;

    player.setHealth(playerNewHealth);
  }

  /**
   * Set stat to item
   *
   * @param item     Item to change
   * @param itemData Item Stat data to put on item
   */
  public void setItemStat(ItemStack item, ItemsData itemData) {
    ItemMeta itemMeta = item.getItemMeta();

    itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "itemStats"), new ItemsDataType(), itemData);

    item.setItemMeta(itemMeta);
  }

}
