package com.promc.Manager;

import com.promc.SUPERPORTAL;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class PortalStorage {
    public static FileConfiguration portalStorage;
    public static File portalFile = new File(
            SUPERPORTAL.getInstance().getDataFolder()
                    + File.separator + "portal.yml");

    public static void load() {
        portalStorage = YamlConfiguration.loadConfiguration(portalFile);
        Set<String> portals = portalStorage.getKeys(false);
        for (String portalId : portals) {
            String region = portalStorage.getString(portalId + ".region");
            Location location = portalStorage.getLocation(portalId + ".location");
            if (portalId != null && region != null && location != null) {
                PortalManager.addPortal(new Portal(portalId, region, location));
            }
        }
    }

    public static void setRegion(String id, String region) {
        portalStorage.set(id + ".region", region);
    }

    public static void setLocation(String id, Location location) {
        portalStorage.set(id + ".location", location);
    }

    /**
     * 创建传送门配置文件
     *
     * @param id 传送门ID
     */
    public static void createPortal(String id) {
        portalStorage.set(id + ".region", "");
        portalStorage.set(id + ".location", "");
    }

    /**
     * 删除指定ID的传送门
     *
     * @param id 传送门ID
     */
    public static void deletePortal(String id) {
        if (portalStorage.contains(id)) {
            portalStorage.set(id, null);
        }
    }

    /**
     * 保存传送门文件
     */
    public static void save() {
        try {
            portalStorage.save(portalFile);
        } catch (IOException ignored) {
            SUPERPORTAL.error("Can't save portal data.");
        }
    }
}
