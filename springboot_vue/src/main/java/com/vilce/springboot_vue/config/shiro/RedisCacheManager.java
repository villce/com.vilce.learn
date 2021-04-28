package com.vilce.springboot_vue.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Description: description
 * @ProjectName: operate
 * @Package: com.eastmoney.emis.operate.config.shiro
 * @Author: 雷才哲
 * @Date: 2021/4/8 下午7:37
 * @Version: 1.0
 */
public class RedisCacheManager implements CacheManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroCache<K, V>(name, redisTemplate);
    }
}
