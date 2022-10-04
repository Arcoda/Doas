package com.github.arcoda.doas.Commands.TabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class DoasAdminTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1 && sender instanceof ConsoleCommandSender) {
            List<String> baseList = new ArrayList<>();
            baseList.add("add");baseList.add("remove");baseList.add("reload");
            return baseList;
        } else {
            return new ArrayList<>();
        }
    }
}
