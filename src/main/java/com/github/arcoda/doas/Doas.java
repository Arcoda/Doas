package com.github.arcoda.doas;

import com.github.arcoda.doas.Commands.DoasAdminCommand;
import com.github.arcoda.doas.Commands.DoasCommand;
import com.github.arcoda.doas.Commands.TabCompleter.DoasAdminTabCompleter;
import com.github.arcoda.doas.Commands.TabCompleter.DoasTabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Doas extends JavaPlugin {
    private static Doas instance;
    @Override
    public void onEnable() {
        instance = this;
        loadConfiguration();
        getCommand("doas").setExecutor(new DoasCommand());
        getCommand("doas").setTabCompleter(new DoasTabCompleter());
        getCommand("doas-admin").setExecutor(new DoasAdminCommand());
        getCommand("doas-admin").setTabCompleter(new DoasAdminTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Doas getInstance() {
        return instance;
    }
    private void loadConfiguration() {
        FileConfiguration config = getConfig();
        config.addDefault("Operator_Have_Access", true);
        config.addDefault("Command_Block_Have_Access", true);
        config.addDefault("Enable_Whitelist", false);
        List exampleList = new ArrayList<>();
        exampleList.add("069a79f4-44e9-4726-a5be-fca90e38aaf5");
        exampleList.add("0b8b2245-8018-456c-945d-4282121e1b1e");
        config.addDefault("Whitelisted_UUID",exampleList);
        config.options().copyDefaults(true);
        saveConfig();
    }
}
