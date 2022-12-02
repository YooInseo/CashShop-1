package com.github.nicks.command;

import com.github.nicks.data.CashShopData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CashShopCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        CashShopData cashShopData = new CashShopData();
        String name;

        if(sender instanceof Player player) {
            if(args.length == 0) {
                player.sendMessage("§a§l[§f§l캐시상점§a§l] §f§l/캐시상점 도움말");
                return true;
            } else if(args[0].equals("도움말")) {
                player.sendMessage("§a§l[§f§l캐시상점§a§l] §f§l/캐시상점 생성 <이름>");
                player.sendMessage("§a§l[§f§l캐시상점§a§l] §f§l/캐시상점 제거 <이름>");
                return true;
            }

            switch (args[0]) {
                case "생성" -> {
                    name = args[1];
                    cashShopData.createCashShop(player, name);
                    return true;
                }

                case "제거" -> {
                    name = args[1];
                    cashShopData.deleteCashShop(player, name);
                    return true;
                }

                case "편집" -> {
                    name = args[1];
                    cashShopData.editCashShop(player, name);
                    return true;
                }
            }
        }
        return false;
    }
}
