package com.promc;

import com.promc.Command.PortalCommand;
import com.promc.File.ConfigFile;
import com.promc.File.LangFile;
import com.promc.Listener.PlayerMoveListener;
import com.promc.Listener.RegionsEnterListener;
import com.promc.Manager.PortalStorage;
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
        Bukkit.getLogger().severe("[SUPERTELEPORT] " + msg);
    }

    public static void info(String msg) {
        Bukkit.getLogger().info("[SUPERTELEPORT] " + msg);
    }

    @Override
    public void onEnable() {
        info("SUPERPORTAL is enabling...");
        if (!Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            error("WorldGuard undetected. Disabling...");
            this.setEnabled(false);
            return;
        }
        SUPERPORTAL.instance = this;
        SUPERPORTAL.pluginManager = Bukkit.getPluginManager();
        regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        ConfigFile.init();
        LangFile.init();
        PortalStorage.load();
        pluginManager.registerEvents(
                new RegionsEnterListener(), this);
        pluginManager.registerEvents(
                new PlayerMoveListener(), this);
        Bukkit.getPluginCommand("superportal")
                .setExecutor(new PortalCommand());
        info("Successfully enabled SUPERPORTAL. By TUCAOEVER");
    }

    @Override
    public void onDisable() {
        info("Thanks for using SUPERPORTAL. By TUCAOEVER");
    }
}
