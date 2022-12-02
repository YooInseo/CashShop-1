package com.github.nicks.command;

import com.github.nicks.data.CashValueData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;


public class CashCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        CashValueData cashValueData = new CashValueData();
        Double amount;
        Player target;

        if (sender instanceof Player player) {

            if (args.length == 0) {
                player.sendMessage("" + cashValueData.getCash(player));
                return true;
            }

            switch (args[0]) {

                case "지급" -> {
                    target = player.getServer().getPlayer(args[1]);
                    amount = Double.parseDouble(args[2]);
                    cashValueData.depositCash(target, amount);
                    return true;
                }

                case "차감" -> {
                    target = player.getServer().getPlayer(args[1]);
                    amount = Double.parseDouble(args[2]);
                    cashValueData.withdrawCash(target, amount);
                    return true;
                }

                case "설정" -> {
                    target = player.getServer().getPlayer(args[1]);
                    amount = Double.parseDouble(args[2]);
                    cashValueData.setCash(target, amount);
                    player.sendMessage(amount + "설정 완료");
                    return true;
                }

                case "확인" -> {
                    target = player.getServer().getPlayer(args[1]);
                    player.sendMessage("" + cashValueData.getCash(target));
                    return true;
                }

                case "모두지급" -> {
                    amount = Double.parseDouble(args[1]);
                    for (Player targetPlayer : player.getServer().getOnlinePlayers()) {
                        cashValueData.depositCash(targetPlayer, amount);
                    }
                    return true;
                }

                case "초기화" -> {
                    target = player.getServer().getPlayer(args[1]);
                    cashValueData.setCash(target, 0.0);
                    player.sendMessage("초기화 완료");
                    return true;
                }
            }
        }
        return false;
    }
}
