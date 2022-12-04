package com.github.nicks.command.tabcomplete;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("all")
public class CashTabComplete implements TabCompleter {


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (sender instanceof Player player) {

            if (player.isOp()) {
                if (args.length == 1) {
                    StringUtil.copyPartialMatches(args[0], Arrays.asList("지급", "차감", "설정", "초기화", "확인", "모두지급"), completions);

                } else if (args.length == 2) {
                    if (args[0].equals("지급") || args[0].equals("차감") || args[0].equals("설정") || args[0].equals("확인")) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            completions.add(target.getName());
                        }
                    }
                } else if (args.length == 3) {
                    if (args[0].equals("지급") || args[0].equals("차감") || args[0].equals("설정")) {
                        StringUtil.copyPartialMatches(args[2], Arrays.asList("<Amount>"), completions);
                    }
                }
            }
        }
        return completions;
    }
}
