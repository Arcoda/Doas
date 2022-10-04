package com.github.arcoda.doas.Commands;

import com.github.arcoda.doas.Doas;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class DoasAdminCommand implements CommandExecutor {
    private final Doas plugin = Doas.getInstance();
    private final FileConfiguration config = plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (!(target == null)) {
                    List<String> uuidList = (List<String>) config.getList("Whitelisted_UUID");
                    if (args[0].equalsIgnoreCase("add")) {
                        uuidList.add(target.getUniqueId().toString());
                        config.set("Whitelisted_UUID", uuidList);
                        plugin.saveConfig();
                        sender.sendMessage("[Doas] Successfully added §n§o" + args[1] + "§r to whitelisted UUIDs");
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (uuidList.remove(target.getUniqueId().toString())) {
                            config.set("Whitelisted_UUID", uuidList);
                            plugin.saveConfig();
                            sender.sendMessage("[Doas] Successfully removed §n§o" + args[1] + "§r from whitelisted UUIDs");
                        } else {
                            sender.sendMessage("[Doas] §4Couldn't find the player in the config");
                        }
                    } else {
                        sender.sendMessage("[Doas] §4Couldn't find the requested method");
                    }
                } else {
                    sender.sendMessage("[Doas] §4Couldn't find the player");
                }
            } else if (args.length == 1) {
                if(args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage("[Doas] Successfully reloaded the config");
                } else {
                    sender.sendMessage("[Doas] §4Couldn't find the requested method");
                }
            } else {
                return false;
            }
        } else {
            sender.sendMessage("[Doas] §4This command is console-only");
        }
        return true;
    }
}
