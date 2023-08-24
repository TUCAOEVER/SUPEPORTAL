package com.promc.file;

import com.promc.SUPERPORTAL;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigFile {

    public static FileConfiguration config;

    public static void init(){
        SUPERPORTAL.getInstance().saveDefaultConfig();
        config = SUPERPORTAL.getInstance().getConfig();

        set("Language", "cn");

        SUPERPORTAL.getInstance().saveConfig();
    }

    public static void set(String key, Object value) {
        config.set(key, value);
    }
}
