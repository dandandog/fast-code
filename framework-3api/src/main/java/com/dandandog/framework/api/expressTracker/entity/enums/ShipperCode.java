package com.dandandog.framework.api.expressTracker.entity.enums;

/**
 * @author JohnnyLiu
 */

public enum ShipperCode {

    /**
     * 百世快递
     */
    HTKY("百世快递"),

    /**
     * 申通
     */
    STO("申通"),

    /**
     * 天天快递
     */
    HHTT("申通"),

    /**
     * 圆通
     */
    YTO("圆通");

    private final String name;

    ShipperCode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
