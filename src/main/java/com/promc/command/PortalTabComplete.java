package com.promc.command;

import com.promc.manager.Portal;
import com.promc.manager.PortalManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PortalTabComplete implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (!(sender instanceof Player)) {
            return list;
        }
        Player player = (Player) sender;
        if (args.length == 1) {
            if (player.hasPermission("superportal.admin")) {
                list.add("create");
                list.add("delete");
                list.add("set");
                list.add("list");
                list.add("reload");
                return list;
            }
            if (player.hasPermission("superportal.create")) list.add("create");
            if (player.hasPermission("superportal.delete")) list.add("delete");
            if (player.hasPermission("superportal.set")) list.add("set");
            if (player.hasPermission("superportal.reload")) list.add("reload");
            if (player.hasPermission("superportal.list")) list.add("list");
        } else if (args.length == 2) {
            switch (args[0]) {
                case "delete":
                    if (player.hasPermission("superportal.admin")
                            || player.hasPermission("superportal.delete")) {
                        for (Portal portal : PortalManager.getAllPortals()) {
                            String portalId = portal.getId();
                            list.add(portalId);
                        }
                    }
                    break;
                case "set":
                    if (player.hasPermission("superportal.admin")
                            || player.hasPermission("superportal.set")) {
                        for (Portal portal : PortalManager.getAllPortals()) {
                            String portalId = portal.getId();
                            list.add(portalId);
                        }
                    }
                    break;
                default:
            }
        } else if (args.length == 3) {
            switch (args[0]) {
                case "set":
                    if (player.hasPermission("superportal.admin")
                            || player.hasPermission("superportal.set")) {
                        list.add("region");
                        list.add("location");
                    }
                    break;
                default:
            }
        }
        return list;
    }
}
