package com.promc.file;

import com.promc.SUPERPORTAL;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LangFile {
    // 语言配置文件
    public static FileConfiguration langConfig;
    // 语言缓存Map
    public static Map<String, String> langMap = new HashMap<>();
    public static File langFile = new File(
            SUPERPORTAL.getInstance().getDataFolder()
                    + File.separator + "locale"
                    + File.separator + "message_" + ConfigFile.config.getString("Language") + ".yml");

    /**
     * 初始化语言文件
     */
    public static void init() {
        FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        add("SUCCESSFULLY_CREATE", "已成功创建传送实例 %1.");
        add("SUCCESSFULLY_DELETE", "已成功删除传送实例 %1.");
        add("SUCCESSFULLY_SET_REGION", "已成功设置传送门 %1 触发区域 %2.");
        add("SUCCESSFULLY_SET_LOCATION", "已成功设置传送门 %1 传送位置 X:%2 Y:%3 Z:%4 W:%5.");
        add("DONT_HAVE_PERMISSION", "你没有足够的权限以执行该命令.");
        add("UNKNOWN_COMMAND", "无效指令");
        add("UNKNOWN_PORTAL", "未知传送门,请检查拼写.");
        add("EMPTY_ARG", "缺少必要参数,请检查后重试.");
        add("PORTAL_ALREADY_EXIST", "传送门已存在,请删除后重试.");
        add("PORTAL_LIST", "当前已加载的传送门:");
        add("SUCCESSFULLY_TELEPORT", "已成功进行传送");
        add("DONT_MATCH_CONDITION", "未满足使用传送门的条件");
        add("RELOAD_SUCCESS", "重载完成");

        for (Map.Entry<String, String> entry : langMap.entrySet()) {
            if (!langConfig.contains(entry.getKey())) {
                langConfig.set(entry.getKey(), entry.getValue());
            }
        }

        try {
            langConfig.save(langFile);
        } catch (IOException ignored) {
            SUPERPORTAL.error("Can't write lang file.");
        }
    }

    /**
     * 重载语言文件
     */
    public static void reload() {
        langConfig = YamlConfiguration.loadConfiguration(langFile);
        for (String key : langConfig.getKeys(false)) {
            langMap.put(key, langConfig.getString(key));
        }
    }

    /**
     * 返回语言值
     * 如果没有设置则返回 ""
     *
     * @param key 语言键
     * @return 语言值
     */
    public static String getLang(String key) {
        return ChatColor.translateAlternateColorCodes(
                '&', langMap.getOrDefault(key, ""));
    }

    /**
     * 添加默认语言键、值
     * 如果值为空则默认设置为 ""
     * 如果键为空则默认跳过
     * 如果键不为空则将获取的值放在 langMap 内用于缓存
     *
     * @param key   语言键
     * @param value 语言值
     */
    public static void add(String key, String value) {
        if (!key.isEmpty()) {
            langMap.put(key, value.isEmpty() ? "" : value);
        } else {
            langMap.put(key, langConfig.getString(value) != null ? langConfig.getString(value) : "");
        }
    }
}
