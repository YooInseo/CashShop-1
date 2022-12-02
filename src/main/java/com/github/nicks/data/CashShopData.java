package com.github.nicks.data;

import com.github.nicks.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static com.github.nicks.CashShop.plugin;
import static com.github.nicks.data.CashShopMapManager.cashShop;

public class CashShopData {

    private ConfigUtils config;


    /**
     * 캐시상점 생성을 생성합니다.
     * @param name 캐시상점 이름
     */
    public void createCashShop(Player player, String name) {
        config = new ConfigUtils("shop/" + name, plugin);
        config.setString("cashshop.name", name);
        config.setString("cashshop.title", name);
        config.setInteger("cashshop.line", 6);
        player.sendMessage("캐시상점 생성 완료");
        cashShop.put(name, Bukkit.createInventory(null, 54, name));
    }


    /**
     * 캐시상점을 삭제합니다.
     * @param name 캐시상점 이름
     */
    public void deleteCashShop(Player player, String name) {
        config = new ConfigUtils("shop/" + name, plugin);
        config.deleteFile();
        player.sendMessage("캐시상점 제거 완료");
    }


    /**
     * 캐시상점을 편집합니다.
     * @param name 캐시상점 이름
     */
    public void editCashShop(Player player, String name) {
        config = new ConfigUtils("shop/" + name, plugin);
//        player.sendMessage(cashShop.get(name).toString());
        player.openInventory(cashShop.get(name));
    }
}
