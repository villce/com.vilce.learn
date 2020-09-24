package com.vilce.common.model.enums;

import java.awt.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.enums.ColorEnum
 * @Author: 雷才哲
 * @Date: 2020/9/23 13:55
 * @Version: 1.0
 */
public enum ColorEnum {

    white(0, Color.white),
    black(1, Color.black),
    gray(2, Color.gray),
    red(3, Color.red),
    orange(4, Color.orange),
    yellow(5, Color.yellow),
    green(6, Color.green),
    blue(7, Color.blue),
    cyan(8, Color.cyan),
    magenta(9, Color.magenta);
    /**
     * 颜色码
     */
    private final int code;
    /**
     * 颜色
     */
    private final Color color;

    public int getCode() {
        return code;
    }

    public Color getColor() {
        return color;
    }

    ColorEnum(int code, Color color) {
        this.code = code;
        this.color = color;
    }

    public static Color getColor(int code) {
        for (ColorEnum colorEnum : values()) {
            if (colorEnum.getCode() == code) {
                return colorEnum.getColor();
            }
        }
        return null;
    }
}
