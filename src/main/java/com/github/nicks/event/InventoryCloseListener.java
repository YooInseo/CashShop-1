package com.github.nicks.event;

import com.github.nicks.utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;


import java.io.File;

import static com.github.nicks.CashShop.plugin;
import static com.github.nicks.data.CashShopMapManager.cashShop;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void closeInventory(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();

        ConfigUtils configFolder = new ConfigUtils("shop/", plugin);
        for (File file : configFolder.getFileList()) {
            ConfigUtils config = new ConfigUtils("shop/" + file.getName().substring(0, file.getName().length() - 4), plugin);

            if (event.getView().getTitle().equals(config.getString("cashshop.title"))) {
                if (cashShop.containsKey(config.getString("cashshop.title"))) {
                    player.sendMessage("테스트");

                    config.setInventory("cashshop", event.getInventory());
                }
                else
                    player.sendMessage("테스트2");
            }

//            for (String name : config.getConfig().getConfigurationSection("cashshop.item").getKeys(false)) {
//                config.setInteger("cashshop.item." + name + ".slot", event.getInventory().getItem(config.getInteger("cashshop.item." + name + ".slot")).getAmount());
//            }
//            player.sendMessage("저장완료");
        }
    }
}
