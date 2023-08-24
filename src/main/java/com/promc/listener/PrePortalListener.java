package com.promc.listener;

import com.promc.event.PrePortalEvent;
import com.promc.manager.Portal;
import com.promc.manager.PortalManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.promc.file.LangFile.getLang;

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
