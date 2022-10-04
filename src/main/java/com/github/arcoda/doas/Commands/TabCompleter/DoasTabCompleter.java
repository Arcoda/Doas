package com.github.arcoda.doas.Commands.TabCompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DoasTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> baseList = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                baseList.add(player.getName());
            }
            baseList.add("c");
            return baseList;
        } else {
            return new ArrayList<>();
        }
    }
}
