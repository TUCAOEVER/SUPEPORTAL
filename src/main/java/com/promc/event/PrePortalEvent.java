package com.promc.event;

import com.promc.manager.Portal;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


/**
 * 玩家使用传送门时触发
 */
public class PrePortalEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    private final Player player;
    private final Portal portal;

    public PrePortalEvent(Player player, Portal portal) {
        this.player = player;
        this.portal = portal;
    }

    public Player getPlayer() {
        return player;
    }

    public Portal getPortal() {
        return portal;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
