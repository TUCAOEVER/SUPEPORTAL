package com.promc.command;

import com.promc.file.LangFile;
import com.promc.manager.Portal;
import com.promc.manager.PortalManager;
import com.promc.manager.PortalStorage;
import com.promc.SUPERPORTAL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.promc.file.LangFile.getLang;

public class PortalCommand implements CommandExecutor {

    /**
     * 创建传送实例
     * 指令：/superteleport create id
     *
     * @param sender  指令发送者
     * @param command 指令
     * @param label   指令别名
     * @param args    指令变量
     * @return 指令是否正确执行
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender);
            if (args.length == 0) {
                player.sendMessage(getLang("EMPTY_ARG"));
                return true;
            }
            switch (args[0]) {
                case "create":
                    if (player.hasPermission("superportal.admin")
                            || player.hasPermission("superportal.create")) {
                        if (args.length == 1) {
                            player.sendMessage(getLang("EMPTY_ARG"));
                            return true;
                        }
                        if (!PortalManager.hasPortal(args[1])) {
                            PortalManager.createPortal(args[1]);
                            player.sendMessage(getLang("SUCCESSFULLY_CREATE")
                                    .replaceAll("%1", args[1]));
                        } else {
                            player.sendMessage(getLang("PORTAL_ALREADY_EXIST"));
                        }
                    } else {
                        player.sendMessage(getLang("DONT_HAVE_PERMISSION"));
                    }
                    break;
                case "delete":
                    if (player.hasPermission("superportal.admin")
                            || player.hasPermission("superportal.delete")) {
                        if (args.length == 1) {
                            player.sendMessage(getLang("EMPTY_ARG"));
                            return true;
                        }
                        if (PortalManager.hasPortal(args[1])) {
                            PortalManager.deletePortal(args[1]);
                            player.sendMessage(getLang("SUCCESSFULLY_DELETE")
                                    .replaceAll("%1", args[1]));
                        } else {
                            player.sendMessage(getLang("UNKNOWN_PORTAL"));
                        }
                    } else {
                        player.sendMessage(getLang("DONT_HAVE_PERMISSION"));
                    }
                    break;
                case "set": // stp 0set 1portalid 2region 3regionname
                    if (args.length > 2) {
                        if (player.hasPermission("superportal.admin")
                                || player.hasPermission("superportal.set")) {
                            if (PortalManager.hasPortal(args[1])) {
                                if (args.length > 3 && args[2].equals("region")) {
                                    PortalManager.setRegion(args[1], args[3]);
                                    player.sendMessage(getLang("SUCCESSFULLY_SET_REGION")
                                            .replaceAll("%1", args[1])
                                            .replaceAll("%2", args[3]));
                                } else if (args.length == 3 && args[2].equals("location")) {
                                    PortalManager.setLocation(args[1], player.getLocation());
                                    player.sendMessage(getLang("SUCCESSFULLY_SET_LOCATION")
                                            .replaceAll("%1", args[1])
                                            .replaceAll("%2", String.format("%.1f", player.getLocation().getX()))
                                            .replaceAll("%3", String.format("%.1f", player.getLocation().getY()))
                                            .replaceAll("%4", String.format("%.1f", player.getLocation().getZ()))
                                            .replaceAll("%5", player.getLocation().getWorld() != null ?
                                                    player.getLocation().getWorld().getName() : "UNKNOWN"));
                                } else {
                                    player.sendMessage(getLang("EMPTY_ARG"));
                                }
                            } else {
                                player.sendMessage(getLang("UNKNOWN_PORTAL"));
                            }
                        } else {
                            player.sendMessage(getLang("DONT_HAVE_PERMISSION"));
                        }
                    } else {
                        player.sendMessage(getLang("EMPTY_ARG"));
                        return true;
                    }
                    break;
                case "list":
                    if (player.hasPermission("superportal.admin")
                            || player.hasPermission("superportal.list")) {
                        for (Portal portal : PortalManager.getAllPortals()) {
                            String id = portal.getId();
                            player.sendMessage(id);
                        }
                    } else {
                        player.sendMessage(getLang("DONT_HAVE_PERMISSION"));
                    }
                    break;
                case "reload":
                    if (player.hasPermission("superportal.admin")
                            || player.hasPermission("superportal.reload")) {
                        SUPERPORTAL.getInstance().reloadConfig();
                        PortalManager.clearPortals();
                        PortalStorage.load();
                        LangFile.reload();
                        player.sendMessage(getLang("RELOAD_SUCCESS"));
                    } else {
                        player.sendMessage(getLang("DONT_HAVE_PERMISSION"));
                    }
                    break;
                default:
                    player.sendMessage(getLang("UNKNOWN_COMMAND"));
            }
        }
        return true;
    }
}
