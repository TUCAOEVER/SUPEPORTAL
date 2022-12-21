package com.promc.Listener;

import com.promc.Event.RegionsEnterEvent;
import com.promc.SUPERPORTAL;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.promc.SUPERPORTAL.pluginManager;
import static com.promc.SUPERPORTAL.regionContainer;

public class PlayerMoveListener implements Listener {

    private final Map<Player, Set<ProtectedRegion>> preRegionMap = new HashMap<>();

    /**
     * 获取玩家所处位置的领地保护区域
     *
     * @param player 玩家
     * @return 领地保护区域
     */
    public static Set<ProtectedRegion> getRegions(Player player) {
        if (player == null || !player.isOnline()) return new HashSet<>();
        RegionQuery regionQuery = regionContainer.createQuery();
        ApplicableRegionSet applicableRegions = regionQuery.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));
        return applicableRegions.getRegions();
    }

    /**
     * 检测玩家移动判断是否进入WG的区域
     * 有且仅在玩家进入的区域改变时触发 RegionsEnterEvent
     *
     * @param event 玩家移动事件
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo() != null ? event.getTo() : from;
        if (from.getX() == to.getX() && from.getZ() == to.getZ() && from.getY() == to.getY()) {
            return;
        }
        Set<ProtectedRegion> curRegionSet = getRegions(player);
        Set<ProtectedRegion> preRegionSet = preRegionMap.get(player);
        if (preRegionMap.containsKey(player)) {
            if (!preRegionSet.equals(curRegionSet)) {
                preRegionMap.put(player, curRegionSet);
                pluginManager.callEvent(new RegionsEnterEvent(player, curRegionSet));
            }
        } else {
            preRegionMap.put(player, curRegionSet);
            pluginManager.callEvent(new RegionsEnterEvent(player, curRegionSet));
        }
    }

}

