package com.github.nicks;

import com.github.nicks.command.CashCmd;
import com.github.nicks.command.CashShopCmd;
import com.github.nicks.command.tabcomplete.CashTabComplete;
import com.github.nicks.event.*;
import com.github.nicks.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static com.github.nicks.data.CashShopMapManager.shopMap;


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

        // 커맨드를 불러옵니다.
        Bukkit.getPluginCommand("캐시").setExecutor(new CashCmd());
        Bukkit.getPluginCommand("캐시").setTabCompleter(new CashTabComplete());
        Bukkit.getPluginCommand("캐시상점").setExecutor(new CashShopCmd());

        // 이벤트를 불러옵니다.
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new CashShopListener(), this);

        // 캐시상점 인벤토리를 불러옵니다.
        ConfigUtils configFolder = new ConfigUtils("shop/", this);
        for (File file : configFolder.getFileList()) {
            ConfigUtils config = new ConfigUtils("shop/" + file.getName().substring(0, file.getName().length() - 4), this);
            shopMap.put(config.getString("cashshop.name"), config.getInventory("cashshop"));
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
