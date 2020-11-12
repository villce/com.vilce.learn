package com.vilce.common.autoconfig.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: redis配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.redis.RedisProperties
 * @Author: 雷才哲
 * @Date: 2020/11/12 17:29
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "spring.vilce.redis")
public class RedisProperties {

    /**
     * 是否开启redis自动化配置
     */
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
