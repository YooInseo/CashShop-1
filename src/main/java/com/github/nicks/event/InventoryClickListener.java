package com.github.nicks.event;

import com.github.nicks.data.GuiType;
import com.github.nicks.utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;

import static com.github.nicks.CashShop.plugin;
import static com.github.nicks.data.CashShopMapManager.typeMap;

public class InventoryClickListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        ConfigUtils configFolder = new ConfigUtils("shop/", plugin);
        for (File file : configFolder.getFileList()) {
            ConfigUtils config = new ConfigUtils("shop/" + file.getName().substring(0, file.getName().length() - 4), plugin);

            if (event.getView().getTitle().equals(config.getString("cashshop.inv.title"))) {
                if (typeMap.containsKey(player) && typeMap.get(player) == GuiType.OPEN) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
