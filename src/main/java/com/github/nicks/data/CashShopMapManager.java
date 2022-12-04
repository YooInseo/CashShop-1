package com.github.nicks.data;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class CashShopMapManager {

    public static Map<String, Inventory> shopMap = new HashMap<>();
    public static Map<Player, GuiType> typeMap = new HashMap<>();
}
