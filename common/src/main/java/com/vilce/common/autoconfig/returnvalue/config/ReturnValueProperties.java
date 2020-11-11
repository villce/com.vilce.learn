package com.vilce.common.autoconfig.returnvalue.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 返参包装配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.returnvalue.config.ReturnValueProperties
 * @Author: 雷才哲
 * @Date: 2020/11/10 19:33
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "spring.vilce.returnvalue")
public class ReturnValueProperties {

    /**
     * 是否启动返参包装配置
     */
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
