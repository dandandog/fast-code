package com.dandandog.framework.api.expressTracker.entity.enums;

/**
 * @author JohnnyLiu
 */

public enum ShipperCode {

    STO("申通快递"),
    YTO("圆通速递"),
    HTKY("百世快递"),
    HHTT("天天快递"),
    SF("顺丰速运"),
    ZTO("中通快递"),
    YD("韵达速递"),
    YZPY("邮政快递包裹"),
    EMS("EMS"),
    JD("京东快递"),
    UC("优速快递"),
    DBL("德邦快递"),
    ZJS("宅急送");

    private final String name;

    ShipperCode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
