package com.promc.event;

import com.promc.manager.Portal;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PortalEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Portal portal;

    public PortalEvent(Player player, Portal portal) {
        this.player = player;
        this.portal = portal;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Portal getPortal() {
        return portal;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
