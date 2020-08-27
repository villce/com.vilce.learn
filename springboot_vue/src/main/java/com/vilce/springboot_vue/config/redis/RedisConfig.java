package com.vilce.springboot_vue.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Description: Redis配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.config.redis.RedisConfig
 * @Author: 雷才哲
 * @Date: 2020/8/26 14:45
 * @Version: 1.0
 */
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * redis序列化方式选择：
     * 1.默认的JdkSerializationRedisSerializer序列化方式，其编码是ISO-8859-1,会出现乱码问题
     * 2.StringRedisSerializer序列化方式，其编码是UTF-8,可以解决乱码问题；序列化字符串无双引号
     * 3.Jackson2JsonRedisSerializer序列化方式，其编码是UTF-8,可以解决乱码问题，但是字符串会有一个双引号
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置Key序列化要使用的模板，默认是JdkSerializationRedisSerializer
        redisTemplate.setKeySerializer(stringSerializer());
        //设置value序列化要使用的模板，默认是JdkSerializationRedisSerializer
        redisTemplate.setValueSerializer(jacksonSerializer());
        //设置次莫版要使用的哈希key(或field)序列化程序，默认是JdkSerializationRedisSerializer
        redisTemplate.setHashKeySerializer(stringSerializer());
        //设置此模板要使用的哈希值序列化程序，默认是JdkSerializationRedisSerializer
        redisTemplate.setHashValueSerializer(jacksonSerializer());
        return redisTemplate;
    }

    /**
     * 初始化string序列化对象
     * @return
     */
    @Bean
    public RedisSerializer stringSerializer() {
        return new StringRedisSerializer();
    }

    /**
     * 初始化jackson序列化对象
     */
    @Bean
    public RedisSerializer jacksonSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //更改自动检测的属性类
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}
