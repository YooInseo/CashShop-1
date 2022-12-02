package com.github.nicks;

import com.github.nicks.command.CashCmd;
import com.github.nicks.command.tabcomplete.CashTabComplete;
import com.github.nicks.event.PlayerJoinListener;
import com.github.nicks.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    public void setConfig() {
        config = new ConfigUtils("config", this);
        config.loadDefaultConfig();
    }


}
