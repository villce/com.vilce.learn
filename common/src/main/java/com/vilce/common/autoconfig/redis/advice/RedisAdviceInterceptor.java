package com.vilce.common.autoconfig.redis.advice;

import com.vilce.common.autoconfig.redis.annotation.RedisFilter;
import com.vilce.common.model.vo.BaseRequest;
import com.vilce.common.utils.HiddenUtils;
import com.vilce.common.utils.JSONUtils;
import com.vilce.common.utils.RequestUtils;
import com.vilce.common.utils.SpecialCharUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


/**
 * @Description: 在接口方法之前进行Redis Advice(增强)处理
 * @ProjectName: trade.api
 * @Package: com.vilce.common.autoconfig.redis.advice.RedisAdviceInterceptor
 * @Author: 雷才哲
 * @Date: 2020/5/14 11:06
 * @Version: 1.0
 */
public class RedisAdviceInterceptor implements MethodInterceptor {

    private RedisTemplate redisTemplate;
    private static final String REDIS_KEY = "com:vilce:springbootVue";
    /**
     * 用来存放不同接口的返回值
     */
    private static final ConcurrentHashMap<String, Map> memoryCache = new ConcurrentHashMap<>();

    public RedisAdviceInterceptor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //获取Method对象
        Method method = invocation.getMethod();
        //获取注解信息
        RedisFilter redisFilter = method.getAnnotation(RedisFilter.class);
        int memoryTime = redisFilter.memoryTime();
        int redisTime = redisFilter.redisTime();
        String uri = RequestUtils.getRequestURI().replaceAll(SpecialCharUtils.LEFT_SLASH, SpecialCharUtils.SEMICOLON);
        String cacheKey = StringUtils.join(REDIS_KEY, uri);
        //获取入参信息
        String params = getParam(invocation);
        if (ObjectUtils.isNotEmpty(params)) {
            cacheKey = StringUtils.join(cacheKey, SpecialCharUtils.SEMICOLON, params);
        }
        // 获取当前时间，以及缓存时间
        Calendar cal = Calendar.getInstance();
        Date nowDate = cal.getTime();
        cal.add(Calendar.SECOND, memoryTime);
        Date cacheDate = cal.getTime();
        //先访问memoryCache
        if (memoryCache.containsKey(cacheKey)) {
            //获取内存缓存中的值
            Map<String, Object> objMap = memoryCache.get(cacheKey);
            //获取缓存时间并进行是否过期判断
            Date memoryDate = (Date) objMap.get("memoryDate");
            if (nowDate.before(memoryDate)) {
                //缓存时间未过期，直接获取值，不再访问Redis和Service层
                RequestUtils.getResponse().setHeader("dataSource", "memoryCache");
                return objMap.get("memoryObject");
            } else {
                //缓存已过期，删除该值
                memoryCache.remove(cacheKey);
            }
        }
        //memoryCache中不存在，或者已过期，继续访问Redis
        Object redisObj = redisTemplate.opsForValue().get(cacheKey);
        if (ObjectUtils.isNotEmpty(redisObj)) {
            //如果存在缓存直接获取值，不再访问service
            RequestUtils.getResponse().setHeader("dataSource", "redis");
            //再将值存入memoryCache
            Map<String, Object> objMap = new HashMap();
            objMap.put("memoryObject", redisObj);
            objMap.put("memoryDate", cacheDate);
            return redisObj;
        }
        //不存在缓存，访问service并将返回值缓存
        RequestUtils.getResponse().setHeader("dataSource", "service");
        Object obj = invocation.proceed();
        if (ObjectUtils.isNotEmpty(obj)) {
            Map<String, Object> objMap = new HashMap();
            objMap.put("memoryObject", obj);
            objMap.put("memoryDate", cacheDate);
            memoryCache.put(cacheKey, objMap);
            redisTemplate.opsForValue().set(cacheKey, obj, redisTime, TimeUnit.SECONDS);
        }
        return obj;
    }

    /**
     * 获取入参字段信息
     *
     * @param invocation
     * @return
     */
    private String getParam(MethodInvocation invocation) {
        Map<String, Object> map = RequestUtils.getParameterMap(RequestUtils.getRequest());
        return StringUtils.join(map.values(), "_");
    }
}
