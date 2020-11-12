package com.vilce.common.autoconfig.log.logback;

import com.vilce.common.model.log.po.LogInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: logback配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.logback.LogbackProperties
 * @Author: 雷才哲
 * @Date: 2020/11/11 10:54
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "spring.vilce.logback")
public class LogbackProperties extends LogInfo {

    /**
     * 是否开启日志配置
     */
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
