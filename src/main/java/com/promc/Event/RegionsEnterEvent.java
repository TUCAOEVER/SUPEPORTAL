package com.promc.Event;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class RegionsEnterEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Set<ProtectedRegion> regions;
    private final Player player;
    private boolean cancelled = false;

    public RegionsEnterEvent(Player player, Set<ProtectedRegion> regions) {
        this.regions = regions;
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Set<ProtectedRegion> getRegions() {
        return regions;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
