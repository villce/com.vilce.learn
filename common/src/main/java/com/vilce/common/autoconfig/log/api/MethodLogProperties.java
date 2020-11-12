package com.vilce.common.autoconfig.log.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: API日志配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.api.MethodLogProperties
 * @Author: 雷才哲
 * @Date: 2020/11/12 11:25
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "spring.vilce.log")
public class MethodLogProperties {

    /**
     * 是否开启方法日志拦截
     */
    private boolean enable;
    /**
     * 是否开启debug模式
     */
    private boolean debug;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
