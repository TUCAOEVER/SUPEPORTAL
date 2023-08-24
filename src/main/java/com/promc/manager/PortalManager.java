package com.promc.manager;

import com.promc.hook.PlaceholderAPIHook;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mvel2.MVEL;

import java.util.ArrayList;

public class PortalManager {
    private static final ArrayList<Portal> portals = new ArrayList<>();

    /**
     * 新增传送门 如果存在已加载的同ID传送门则不做任何操作
     *
     * @param id 传送门ID
     */
    public static void createPortal(String id) {
        if (!hasPortal(id)) {
            PortalStorage.createPortal(id);
            portals.add(new Portal(id));
            PortalStorage.save();
        }
    }

    /**
     * 新增携带完整数据的传送门
     *
     * @param id       传送门ID
     * @param region   传送门区域
     * @param location 传送至的位置
     */
    public static void createPortal(String id, String region, Location location) {
        if (!hasPortal(id)) {
            PortalStorage.createPortal(id);
            PortalStorage.setRegion(id, region);
            PortalStorage.setLocation(id, location);
            portals.add(new Portal(id));
            PortalStorage.save();
        }
    }

    /**
     * 设置指定序号的传送门的传送位置
     *
     * @param id       传送门ID
     * @param location 传送至的位置
     */
    public static void setLocation(String id, Location location) {
        Portal portal = getPortal(id);
        if (portal != null) {
            portal.setLocation(location);
            PortalStorage.setLocation(id, location);
            PortalStorage.save();
        }
    }

    /**
     * 通过ID获取传送门实例
     *
     * @param id 传送门ID
     * @return 指定ID的传送门
     */
    public static Portal getPortal(String id) {
        for (Portal portal : portals) {
            if (portal.getId().equals(id)) {
                return portal;
            }
        }
        return null;
    }

    /**
     * 从Portal List里删除指定ID的传送门
     *
     * @param id 传送门ID
     */
    public static void deletePortal(String id) {
        Portal portal = getPortal(id);
        if (portal != null) {
            portals.remove(portal);
            PortalStorage.deletePortal(id);
            PortalStorage.save();
        }
    }

    /**
     * 加载数据中是否存在以某一ID的传送门
     *
     * @param id 传送门ID
     * @return 是否存在该传送门
     */
    public static boolean hasPortal(String id) {
        for (Portal portal : portals) {
            if (portal.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPortalRegion(String region) {
        for (Portal portal : portals) {
            String regionName = portal.getRegion();
            if (regionName.equals(region)) {
                return true;
            }
        }
        return false;
    }

    public static Portal getPortalFromRegion(String region) {
        for (Portal portal : portals) {
            String regionName = portal.getRegion();
            if (regionName.equals(region)) {
                return portal;
            }
        }
        return null;
    }

    /**
     * 设置传送门的区域
     *
     * @param id     传送门ID
     * @param region 需要设置的传送门区域
     */
    public static void setRegion(String id, String region) {
        Portal portal = getPortal(id);
        if (portal != null) {
            portal.setRegion(region);
            PortalStorage.setRegion(id, region);
            PortalStorage.save();
        }
    }

    /**
     * 设置传送门的使用条件
     *
     * @param id        传送门ID
     * @param condition 传送门条件
     */
    public static void setCondition(String id, String condition) {
        Portal portal = getPortal(id);
        if (portal != null) {
            portal.setCondition(condition);
            PortalStorage.setCondition(id, condition);
            PortalStorage.save();
        }
    }

    /**
     * 检查玩家是否有使用传送门的权限
     * 如果结果返回不为布尔值则返回ture
     * 如果没有设置传送门条件则返回true
     * 如果没有开启PlaceholdersAPI则返回true
     *
     * @param portal 传送门
     * @param player 玩家
     * @return 是否拥有使用传送门权限
     */
    public static boolean checkCondition(Player player, Portal portal) {
        if (portal.hasCondition() && PlaceholderAPIHook.isEnabled) {
            Object result = MVEL.eval(PlaceholderAPI.setPlaceholders(player, portal.getCondition()));
            return result instanceof Boolean ? (Boolean) result : true;
        }
        return true;
    }

    public static ArrayList<Portal> getAllPortals() {
        return portals;
    }

    public static void addPortal(Portal portal) {
        portals.add(portal);
    }

    public static void removePortal(Portal portal) {
        portals.remove(portal);
    }

    /**
     * 判断传送门是否拥有完整的设置
     *
     * @param portal 传送门ID
     * @return 是否完整
     */
    public static boolean isValid(Portal portal) {
        return portal.getId() != null && portal.getRegion() != null && portal.getLocation() != null;
    }

    public static void clearPortals() {
        portals.clear();
    }
}
