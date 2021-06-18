package com.github.poxiton;

import com.github.poxiton.utilities.ItemsData;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveItemCommand implements CommandExecutor {

    private StatsManager manager;

    public GiveItemCommand(StatsManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rpgitem")) {
            Player player = (Player) sender;

            manager.setItemStat(player.getInventory().getItemInMainHand(), new ItemsData() {
                {
                    lifesteal = 0.1f;
                }
            });
        }
        return true;
    }
}
