package com.vilce.springboot_vue.config.shiro;

import org.apache.ibatis.cache.CacheException;
import org.apache.shiro.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: description
 * @ProjectName: operate
 * @Package: com.eastmoney.emis.operate.config.shiro
 * @Author: 雷才哲
 * @Date: 2021/4/8 下午7:38
 * @Version: 1.0
 */
public class ShiroCache<K, V> implements Cache<K, V> {

    private static final String SHIRO_SESSION = "com:vilce:shiro:";
    private static final int SESSION_TIME = 1800;
    private RedisTemplate<K, V> redisTemplate;

    public ShiroCache(String name, RedisTemplate client) {
        this.redisTemplate = client;
    }

    @Override
    public V get(K key) throws CacheException {
        redisTemplate.boundValueOps(getCacheKey(key)).expire(SESSION_TIME, TimeUnit.MINUTES);
        return redisTemplate.boundValueOps(getCacheKey(key)).get();
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V old = get(key);
        redisTemplate.boundValueOps(getCacheKey(key)).set(value);
        return old;
    }

    @Override
    public V remove(K key) throws CacheException {
        V old = get(key);
        redisTemplate.delete(getCacheKey(key));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    private K getCacheKey(Object k) {
        return (K) (SHIRO_SESSION + k);
    }
}
