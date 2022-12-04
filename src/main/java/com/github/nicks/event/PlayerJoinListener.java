package com.github.nicks.event;

import com.github.nicks.utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.github.nicks.CashShop.plugin;


@SuppressWarnings("all")
public class PlayerJoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ConfigUtils config = new ConfigUtils("data/" + player.getUniqueId(), plugin);
        if(!config.isFileExist()) {
            config.setDouble("playerdata.cash", 0.0);
        }
    }
}
