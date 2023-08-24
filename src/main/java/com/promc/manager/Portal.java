package com.promc.manager;

import org.bukkit.Location;

public class Portal {
    private String id;
    private Location location;
    private String region;
    private String condition;

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
     * 获取传送门使用条件
     * @return MVEL表达式
     */
    public String getCondition() {
        return condition;
    }

    /**
     * 设置传送门使用条件
     * @param condition MVEL表达式
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * 判断传送门设置了条件
     * @return 是否存在条件
     */
    public boolean hasCondition() {
        return condition != null;
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
