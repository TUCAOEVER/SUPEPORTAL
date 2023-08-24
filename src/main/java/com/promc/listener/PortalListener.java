package com.promc.listener;

import com.promc.event.PortalEvent;
import com.promc.manager.Portal;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.promc.file.LangFile.getLang;

public class PortalListener implements Listener {
    /**
     * 触发传送
     * @param event 传送门传送事件
     */
    @EventHandler
    public void onPortalTeleport(PortalEvent event) {
        Player player = event.getPlayer();
        Portal portal = event.getPortal();
        Location location = portal.getLocation();
        player.teleport(location);
        player.sendMessage(getLang("SUCCESSFULLY_TELEPORT"));
    }
}
