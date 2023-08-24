package com.promc;

import com.promc.command.PortalCommand;
import com.promc.command.PortalTabComplete;
import com.promc.event.Entry;
import com.promc.file.ConfigFile;
import com.promc.file.LangFile;
import com.promc.listener.PortalListener;
import com.promc.listener.PrePortalListener;
import com.promc.listener.RegionsEnterListener;
import com.promc.manager.PortalStorage;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SUPERPORTAL extends JavaPlugin {

    public static RegionContainer regionContainer;
    public static Plugin instance;
    public static PluginManager pluginManager;

    public static Plugin getInstance() {
        return instance;
    }

    public static void error(String msg) {
        Bukkit.getLogger().severe("[SUPERPORTAL] " + msg);
    }

    public static void info(String msg) {
        Bukkit.getLogger().info("[SUPERPORTAL] " + msg);
    }

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            error("WorldGuard undetected. Disabling...");
            this.setEnabled(false);
            return;
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            info("PlaceholderAPI undetected. Condition may not work.");
        }
        SUPERPORTAL.instance = this;
        SUPERPORTAL.pluginManager = Bukkit.getPluginManager();

        regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        WorldGuard.getInstance().getPlatform().getSessionManager().registerHandler(Entry.factory, null);

        ConfigFile.init();
        LangFile.init();
        PortalStorage.load();

        pluginManager.registerEvents(new RegionsEnterListener(), this);
        pluginManager.registerEvents(new PortalListener(), this);
        pluginManager.registerEvents(new PrePortalListener(), this);

        Bukkit.getPluginCommand("superportal")
                .setExecutor(new PortalCommand());
        Bukkit.getPluginCommand("superportal")
                .setTabCompleter(new PortalTabComplete());

        info("Successfully enabled SUPERPORTAL. By TUCAOEVER");
    }

    @Override
    public void onDisable() {
        info("Thanks for using SUPERPORTAL. By TUCAOEVER");
    }
}
