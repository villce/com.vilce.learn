package com.vilce.common.model.enums;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.enums.DateEnum
 * @Author: 雷才哲
 * @Date: 2019/12/20 10:44
 * @Version: 1.0
 */
public enum DateEnum {
    YYYY_MM("yyyy-MM"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
    YYYY_MM_DD_T_HH_MM_SS_SSS_Z("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    YYYY("yyyy"),
    YYYYMM("yyyyMM"),
    YYYYMMDD("yyyyMMdd"),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    YYYY_MM_EN("yyyy/MM"),
    YYYY_MM_DD_EN("yyyy/MM/dd"),
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),
    YYYY_MM_CN("yyyy年MM月"),
    YYYY_MM_DD_CN("yyyy年MM月dd日"),
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),
    HH_MM("HH:mm"),
    HH_MM_SS("HH:mm:ss"),
    YYYY_MM_SPOT("yyyy.MM"),
    YYYY_MM_DD_SPOT("yyyy.MM.dd"),
    MM_DD("MM-dd"),
    MM_DD_HH_MM("MM-dd HH:mm"),
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),
    MM_DD_EN("MM/dd"),
    MM_DD_HH_MM_EN("MM/dd HH:mm"),
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),
    MM_DD_CN("MM月dd日"),
    MM_DD_HH_MM_CN("MM月dd日 HH:mm"),
    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss");

    private final String format;

    private DateEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return this.format;
    }
}
