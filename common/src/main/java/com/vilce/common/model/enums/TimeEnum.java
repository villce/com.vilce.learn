package com.vilce.common.model.enums;

/**
 * @Description: 时间单位
 * @ProjectName: com.eastmoney.emis.utils
 * @Package: com.eastmoney.emis.utils.common.enums.TimeEnum
 * @Author: 雷才哲
 * @Date: 2019/11/27 13:45
 * @Version: 1.0
 */
public enum TimeEnum {
    /**
     * 时间单位
     */
    SECOND("s", "秒"),
    MINUTE("m", "分钟"),
    HOUR("H", "小时"),
    DAY("d", "天");

    /**
     * 状态码
     */
    private final String status;
    /**
     * 描述字段
     */
    private final String message;

    TimeEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
