package com.promc.listener;

import com.promc.event.PortalEvent;
import com.promc.event.PrePortalEvent;
import com.promc.event.RegionsEnteredEvent;
import com.promc.manager.Portal;
import com.promc.manager.PortalManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Set;

import static com.promc.SUPERPORTAL.pluginManager;


public class RegionsEnterListener implements Listener {

    @EventHandler
    public void onRegionsEnter(RegionsEnteredEvent event) {
        Set<String> regionsNames = event.getRegionsNames();
        if (regionsNames.size() == 0) return;
        for (String regionName: regionsNames) {
            Portal portal = PortalManager.getPortalFromRegion(regionName);
            if (portal != null && PortalManager.isValid(portal)) {
                Player player = event.getPlayer();

                PrePortalEvent prePortalEvent = new PrePortalEvent(player, portal);
                pluginManager.callEvent(prePortalEvent);
                if (prePortalEvent.isCancelled()) return;

                PortalEvent portalEvent = new PortalEvent(player, portal);
                pluginManager.callEvent(portalEvent);
                break;
            }
        }
    }
}
