package com.vilce.common.autoconfig.converter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: jackson转换配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.converter.Jackson2MessagesProperties
 * @Author: 雷才哲
 * @Date: 2020/11/12 15:10
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "spring.vilce.jackson")
public class Jackson2MessagesProperties {

    /**
     * 是否开启jackson转换配置
     */
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
