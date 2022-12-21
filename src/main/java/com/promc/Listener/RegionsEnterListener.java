package com.promc.Listener;

import com.promc.Event.PortalEvent;
import com.promc.Event.PrePortalEvent;
import com.promc.Event.RegionsEnterEvent;
import com.promc.Manager.Portal;
import com.promc.Manager.PortalManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Set;

import static com.promc.SUPERPORTAL.pluginManager;


public class RegionsEnterListener implements Listener {

    @EventHandler
    public void onRegionsEnter(RegionsEnterEvent event) {
        Set<ProtectedRegion> regionName = event.getRegions();
        if (regionName.size() == 0) return;
        for (ProtectedRegion region: regionName) {
            String regionId = region.getId();
            Portal portal = PortalManager.getPortalFromRegion(regionId);
            if (portal != null && PortalManager.isValid(portal)) {
                Player player = event.getPlayer();
                PrePortalEvent prePortalEvent = new PrePortalEvent(player, portal);
                pluginManager.callEvent(prePortalEvent);
                if (prePortalEvent.isCancelled()) return;
                PortalEvent portalEvent = new PortalEvent(player, portal);
                pluginManager.callEvent(portalEvent);
                return;
            }
        }
    }
}
