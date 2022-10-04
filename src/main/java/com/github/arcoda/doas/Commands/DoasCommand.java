package com.github.arcoda.doas.Commands;

import com.github.arcoda.doas.Doas;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DoasCommand implements CommandExecutor {
    private final FileConfiguration config = Doas.getInstance().getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                answer(sender, "Please input a player and text/command to send");
                return true;
            case 1:
                answer(sender, "Please input a text/command to send");
                return true;
            default:
            /*Object Player;
            if (sender instanceof ConsoleCommandSender) {}
            else if (plugin.getConfig().getBoolean("Enable_Whitelist") && !plugin.getConfig().getList("Whitelisted_UUID").contains())*/
                if (sender instanceof Player) {
                    if (config.getBoolean("Enable_Whitelist")) {
                        if(!config.getList("Whitelisted_UUID").contains(((Player)sender).getUniqueId().toString())) {
                            answer(sender, "§4You are not whitelisted to use this command");
                            return true;
                        }
                    }
                    //Permission checks
                    if (sender.isOp() && config.getBoolean("Operator_Have_Access")) {
                        //Skip checks if op and op access enabled
                    } else if (args[0].equalsIgnoreCase("c") && !sender.hasPermission("doas.console")) {
                        //Check if player is trying to execute a command as console with "c"
                        answer(sender);
                        return true;
                    } else if (args[1].startsWith("/") && !sender.hasPermission("doas.command")){
                        answer(sender);
                        return true;
                    } else if (!args[0].equalsIgnoreCase("c") && !sender.hasPermission("doas.talk")) {
                        answer(sender);
                        return true;
                    }
                } else if (sender instanceof BlockCommandSender) {
                    if (!config.getBoolean("Command_Block_Have_Access")) {
                        answer(sender, "§4Doas in command block is disabled");
                        return true;
                    }
                } else if (sender instanceof ConsoleCommandSender) {}
                else {
                    return false;
                }
                //EXECUTION
                //Get all the command/chat into one string
                StringBuilder commandArgsBuilder = new StringBuilder();
                for(int i = 1; i < args.length; i++){
                    commandArgsBuilder.append(args[i]).append(" ");
                }
                commandArgsBuilder.deleteCharAt(commandArgsBuilder.length() - 1);
                String commandArgs = commandArgsBuilder.toString();
                //Check if executing as console
                if (args[0].equalsIgnoreCase("c")) {
                    if (Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                            commandArgs.startsWith("/") ? commandArgs.substring(1) : commandArgs))
                    {
                        answer(sender, "Successfully executed §n§o"+commandArgs+"§r as §n§oConsole");
                    } else {
                        answer(sender, "The requested command doesn't exist.");
                    }
                    return true;
                }
                //Find player
                Player target = Bukkit.getPlayer(args[0]);
                 if (target == null) {
                     answer(sender, "Couldn't find the target player");
                 } else {
                     //Check whether to execute a command or chat
                     if (commandArgs.startsWith("/")) {
                         if (target.performCommand(commandArgs.substring(1))) {
                             answer(sender, "Successfully executed §n§o"+commandArgs+"§r as §n§o"+args[0]);
                         } else {
                             answer(sender, "The requested command doesn't exist.");
                         }
                     } else {
                        target.chat(commandArgs);
                        answer(sender, "Successfully said §n§o"+commandArgs+"§r as §n§o"+args[0]);
                     }
                 }
                return true;
        }
    }
    private void answer(CommandSender sender, String text) {
        sender.sendMessage("[Doas] "+text);
    }
    private void answer(CommandSender sender) {
        sender.sendMessage("[Doas] §4You don't have permission to use this command");
    }
}
