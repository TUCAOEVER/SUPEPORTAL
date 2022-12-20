package com.promc.Listener;

import com.promc.Event.RegionsEnterEvent;
import com.promc.Manager.PortalManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;

import static com.promc.SUPERPORTAL.pluginManager;
import static com.promc.SUPERPORTAL.regionContainer;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Set<ProtectedRegion> regionSet = getRegions(player);
        pluginManager.callEvent(new RegionsEnterEvent(player, regionSet));
    }

    /**
     * 获取玩家所处位置的领地保护区域
     * @param player 玩家
     * @return 领地保护区域
     */
    public static Set<ProtectedRegion> getRegions(Player player) {
        if (player != null && !player.isOnline()) return new HashSet<>();
        RegionQuery regionQuery = regionContainer.createQuery();
        ApplicableRegionSet applicableRegions = regionQuery.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));
        return applicableRegions.getRegions();
    }
}
