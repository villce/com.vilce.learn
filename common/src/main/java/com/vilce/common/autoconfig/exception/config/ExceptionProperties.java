package com.vilce.common.autoconfig.exception.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 异常配置类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.exception.config.ExceptionProperties
 * @Author: 雷才哲
 * @Date: 2020/11/11 9:58
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "spring.vilce.exception")
public class ExceptionProperties {

    /**
     * 是否开启异常捕获
     */
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
