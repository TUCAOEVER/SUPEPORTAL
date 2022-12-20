package com.promc.Manager;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class Portal {
    private String id;
    private Location location;
    private String region;

    /**
     * 创建传送门实例
     *
     * @param id       传送门ID
     * @param region   传送门区域
     * @param location 传送至的位置
     */
    public Portal(String id, String region, Location location) {
        this.id = id;
        this.region = region;
        this.location = location;
    }

    /**
     * 通过ID创建传送门对象
     *
     * @param id 传送门ID
     */
    public Portal(String id) {
        this.id = id;
    }


    /**
     * 获取传送门的ID
     *
     * @return 传送门的ID
     */
    public String getId() {
        return id;
    }

    /**
     * 获取传送门传送至的位置
     *
     * @return 传送门传送至的位置
     */
    public Location getLocation() {
        return location;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
