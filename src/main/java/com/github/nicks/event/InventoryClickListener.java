package com.github.nicks.event;

import com.github.nicks.data.CashAPI;
import com.github.nicks.data.GuiType;
import com.github.nicks.events.CashShopPurchaseEvent;
import com.github.nicks.events.CashShopSellEvent;
import com.github.nicks.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Arrays;

import static com.github.nicks.CashShop.plugin;
import static com.github.nicks.data.CashShopMapManager.guiType;

public class InventoryClickListener implements Listener {


    /**
     * 캐시상점 열기
     *
     * @param event 이벤트 캔슬
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        ConfigUtils configFolder = new ConfigUtils("shop/", plugin);
        for (File file : configFolder.getFileList()) {
            ConfigUtils config = new ConfigUtils("shop/" + file.getName().substring(0, file.getName().length() - 4), plugin);

            if (event.getView().getTitle().equals(config.getString("cashshop.inv.title"))) {
                if (guiType.containsKey(player) && guiType.get(player) == GuiType.OPEN) {
                    event.setCancelled(true);
                }
            }
        }
    }

    /**
     * 캐시상점 구매 & 판매
     *
     * @param event
     */
    @EventHandler
    public void onTrade(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        ConfigUtils data = new ConfigUtils("data/" + player.getUniqueId(), plugin);
        ConfigUtils configFolder2 = new ConfigUtils("shop/", plugin);
        CashAPI cashAPI = new CashAPI();


        for (File file2 : configFolder2.getFileList()) {
            ConfigUtils config = new ConfigUtils("shop/" + file2.getName().substring(0, file2.getName().length() - 4), plugin);
            if (event.getView().getTitle().equals(config.getString("cashshop.inv.title"))) {
                if (guiType.containsKey(player) && guiType.get(player) == GuiType.OPEN) {
                    if(event.getCurrentItem() == null) return;

                    if (config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.buy") == -1) {
                        System.out.println((event.getClick() == ClickType.LEFT ? "구매" : "판매") + " 불가능한 상품입니다.");

                        return;
                    }

                    // 캐시상점 1개 구매
                    if (event.getClick() == ClickType.LEFT) {
                        if (data.getDouble("playerdata.cash") < config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.buy")) {
                            System.out.println("돈이 부족합니다.");
                        } else {
                            if (!isInventoryFull(player)) {

                                CashShopPurchaseEvent cashShopPurchaseEvent = new CashShopPurchaseEvent(player);
                                cashShopPurchaseEvent.setPurchaseType(CashShopPurchaseEvent.PurchaseType.SINGLE);
                                Bukkit.getPluginManager().callEvent(cashShopPurchaseEvent);

                                player.getInventory().addItem(event.getCurrentItem()); // 인벤토리에 아이템 지급
                                cashAPI.withdrawCash(player, config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.buy")); // 캐시 차감
                            } else {
                                player.sendMessage("인벤토리가 가득차서 구매가 불가능합니다!");
                            }
                        }

                        // 캐시상점 1개 판매
                    } else if (event.getClick() == ClickType.RIGHT) {
                        if (Arrays.asList(player.getInventory().getContents()).contains(event.getCurrentItem())) { // 플레이어 인벤토리에 아이템이 있는지 체크

                            CashShopSellEvent cashShopSellEvent = new CashShopSellEvent(player);
                            cashShopSellEvent.setSellType(CashShopSellEvent.SellType.SINGLE);
                            Bukkit.getPluginManager().callEvent(cashShopSellEvent);

                            player.getInventory().removeItem(event.getCurrentItem()); // 아이템 제거
                            cashAPI.depositCash(player, config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.sell")); // 캐시 추가
                            System.out.println(event.getSlot() + "의 판매 가격은 " + config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.sell") + "입니다.");
                        } else {
                            player.sendMessage("아이템이 없어 판매가 불가능합니다!");
                        }
                    }

                    // 캐시상점 64개 구매
                    if (event.getClick() == ClickType.SHIFT_LEFT) {
                        if (data.getDouble("playerdata.cash") * 64 < config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.buy") * 64) {
                            System.out.println("돈이 부족합니다.");
                        } else {
                            if (!isInventoryFull(player)) {
                                player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType(), 64)); // 인벤토리에 아이템 지급

                                CashShopPurchaseEvent cashShopPurchaseEvent = new CashShopPurchaseEvent(player);
                                cashShopPurchaseEvent.setPurchaseType(CashShopPurchaseEvent.PurchaseType.SET);
                                Bukkit.getPluginManager().callEvent(cashShopPurchaseEvent);

                                cashAPI.withdrawCash(player, config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.buy") * 64); // 캐시 차감
                            } else {
                                player.sendMessage("인벤토리가 가득차서 구매가 불가능합니다!");
                            }
                        }
                    }

                    // 캐시상점 64개 판매
                    if (event.getClick() == ClickType.SHIFT_RIGHT) {
                        if (Arrays.asList(player.getInventory().getContents()).contains(new ItemStack(event.getCurrentItem().getType(), 64))) { // 플레이어 인벤토리에 아이템이 있는지 체크

                            CashShopSellEvent cashShopSellEvent = new CashShopSellEvent(player);
                            cashShopSellEvent.setSellType(CashShopSellEvent.SellType.SET);
                            Bukkit.getPluginManager().callEvent(cashShopSellEvent);

                            player.getInventory().removeItem(new ItemStack(event.getCurrentItem().getType(), 64)); // 아이템 제거
                            cashAPI.depositCash(player, config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.sell") * 64); // 캐시 추가
                            System.out.println(event.getSlot() + "의 판매 가격은 " + config.getDouble("cashshop.inv.items." + event.getSlot() + ".Price.sell") * 64 + "입니다.");
                        } else {
                            player.sendMessage("아이템이 없어 판매가 불가능합니다!");

                        }
                    }
                }
            }
        }
    }


    /**
     * 플레이어 인벤토리가 가득찼는지 체크 합니다.
     *
     * @param player
     * @return
     */
    public boolean isInventoryFull(Player player) {
        int slot = player.getInventory().firstEmpty();
        return slot == -1;
    }
}
