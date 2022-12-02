package com.github.nicks;

import com.github.nicks.command.CashCmd;
import com.github.nicks.command.CashShopCmd;
import com.github.nicks.command.tabcomplete.CashTabComplete;
import com.github.nicks.event.InventoryCloseListener;
import com.github.nicks.event.PlayerJoinListener;
import com.github.nicks.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static com.github.nicks.data.CashShopMapManager.cashShop;


@SuppressWarnings("all")
public class CashShop extends JavaPlugin {

    public static CashShop plugin;
    private ConfigUtils config;


    /**
     * 플러그인 활성화
     */
    public void onEnable() {
        plugin = this;
        setConfig();
        init();
    }

    /**
     * 이벤트 & 커맨드 활성화
     */
    public void init() {
        Bukkit.getPluginCommand("캐시").setExecutor(new CashCmd());
        Bukkit.getPluginCommand("캐시").setTabCompleter(new CashTabComplete());
        Bukkit.getPluginCommand("캐시상점").setExecutor(new CashShopCmd());

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);

        ConfigUtils configFolder = new ConfigUtils("shop/", this);
        for (File file : configFolder.getFileList()) {
            ConfigUtils config = new ConfigUtils("shop/" + file.getName().substring(0, file.getName().length() - 4), this);

            cashShop.put(config.getString("cashshop.name"), config.getInventory("cashshop"));
        }
    }

    /**
     * 콘피그 설정
     */
    public void setConfig() {
        config = new ConfigUtils("config", this);
        config.loadDefaultConfig();
    }


}
