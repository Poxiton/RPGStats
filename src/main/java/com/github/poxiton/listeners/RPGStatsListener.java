package com.github.poxiton.listeners;

import com.github.poxiton.StatsManager;
import com.github.poxiton.utilities.ItemsDataType;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;

public class RPGStatsListener implements Listener {

    private StatsManager manager;
    private Plugin plugin;

    public RPGStatsListener(Plugin plugin, StatsManager manager) {
        this.plugin = plugin;
        this.manager = manager;
    }

    @EventHandler
    public void PlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player))
            return;

        Player player = (Player) event.getDamager();
        ItemStack item = player.getInventory().getItemInMainHand();

        // This check item type
        if (item.getType() == Material.AIR)
            return;

        PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();

        // Checking if an item has a given data
        if (!itemData.has(new NamespacedKey(plugin, "itemStats"), new ItemsDataType()))
            return;

        manager.healPlayer(player, item, event.getDamage());
    }
}