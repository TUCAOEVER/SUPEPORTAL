package com.promc.Listener;

import com.promc.Event.PortalTeleportEvent;
import com.promc.Event.RegionsEnterEvent;
import com.promc.Manager.Portal;
import com.promc.Manager.PortalManager;
import com.promc.SUPERPORTAL;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Set;

import static com.promc.File.LangFile.getLang;
import static com.promc.SUPERPORTAL.pluginManager;


public class RegionsEnterListener implements Listener {

    @EventHandler
    public void onRegionsEnter(RegionsEnterEvent event) {
        Set<ProtectedRegion> regionName = event.getRegions();
        if (regionName.size() == 0) return;
        for (ProtectedRegion region: regionName) {
            String regionId = region.getId();
            Portal portal = PortalManager.getPortalFromRegion(regionId);
            if (portal != null) {
                PortalTeleportEvent portalEvent = new PortalTeleportEvent(event.getPlayer(), portal);
                pluginManager.callEvent(portalEvent);
                if (portalEvent.isCancelled()) return;
                // 判断传送门是否完整
                if (!PortalManager.isValid(portal)) return;
                Location location = portal.getLocation();
                event.getPlayer().teleport(location);
                event.getPlayer().sendMessage(getLang("SUCCESSFULLY_TELEPORT"));
                return;
            }
        }
    }
}
