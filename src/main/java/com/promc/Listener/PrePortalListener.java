package com.promc.Listener;

import com.promc.Event.PrePortalEvent;
import com.promc.Manager.Portal;
import com.promc.Manager.PortalManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.promc.File.LangFile.getLang;

public class PrePortalListener implements Listener {

    @EventHandler
    public void onPrePortalTeleport(PrePortalEvent event) {
        Player player = event.getPlayer();
        Portal portal = event.getPortal();
        if (player != null && portal!=null) {
            if (!PortalManager.checkCondition(player, portal)){
                event.setCancelled(true);
                player.sendMessage(getLang("DONT_MATCH_CONDITION"));
            }
        }
    }
}
