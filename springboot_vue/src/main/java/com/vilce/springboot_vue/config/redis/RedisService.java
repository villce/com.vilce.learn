package com.vilce.springboot_vue.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Redis服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.config.redis.RedisService
 * @Author: 雷才哲
 * @Date: 2020/8/26 14:45
 * @Version: 1.0
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Long delete(Set<String> keys) {
        return redisTemplate.delete(keys);
    }

    public Set<String> getKeysByPattern(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
