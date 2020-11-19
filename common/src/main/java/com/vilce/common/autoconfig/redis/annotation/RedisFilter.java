package com.vilce.common.autoconfig.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: Redis拦截注解
 * @ProjectName: trade.api
 * @Package: com.vilce.common.autoconfig.redis.annotation.RedisFilter
 * @Author: 雷才哲
 * @Date: 2020/5/14 10:40
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisFilter {

    /**
     * 本地缓存时间(秒)
     *
     * @return
     */
    int memoryTime() default 10;

    /**
     * redis缓存时间(秒)
     *
     * @return
     */
    int redisTime() default 300;
}
