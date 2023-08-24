package com.promc.event;

import com.promc.SUPERPORTAL;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.Handler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Entry extends Handler implements Listener {
    public static final Factory factory = new Factory();

    public Entry(Session session) {
        super(session);
    }

    @Override
    public boolean onCrossBoundary(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, Set<ProtectedRegion> entered, Set<ProtectedRegion> left, MoveType moveType) {
        PluginManager pluginManager = SUPERPORTAL.getInstance().getServer().getPluginManager();


        // 获取进入领域与离开领域的交集
        Set<String> enteredNames = entered.stream().map(ProtectedRegion::getId).collect(Collectors.toSet());
        Set<String> leftNames = left.stream().map(ProtectedRegion::getId).collect(Collectors.toSet());
        //Bukkit.getLogger().info("ini " + enteredNames + leftNames);
        Set<String> difference = new HashSet<>(enteredNames);
        difference.retainAll(leftNames);

        enteredNames.removeAll(difference);
        if (!enteredNames.isEmpty()) {
            //Bukkit.getLogger().info("enter " + enteredNames);
            Set<ProtectedRegion> enteredRegions = entered.stream().filter(protectedRegion ->
                    enteredNames.contains(protectedRegion.getId())).collect(Collectors.toSet());
            RegionsEnteredEvent event = new RegionsEnteredEvent(player.getUniqueId(), enteredRegions);
            pluginManager.callEvent(event);
            if (event.isCancelled()) return false;
        }

        return true;
    }

    public static class Factory extends Handler.Factory<Entry> {
        @Override
        public Entry create(Session session) {
            return new Entry(session);
        }
    }
}
