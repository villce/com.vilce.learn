package com.vilce.springboot_vue.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Description: description
 * @ProjectName: operate
 * @Package: com.eastmoney.emis.operate.config.shiro
 * @Author: 雷才哲
 * @Date: 2021/4/8 下午6:19
 * @Version: 1.0
 */
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String SHIRO_SESSION = "com:vilce:shiro:";
    private static final int SESSION_TIME = 1800;

    /**
     * 创建session，保存到redis数据库
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        try {
            redisTemplate.opsForValue().set(SHIRO_SESSION + sessionId.toString(), session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionId;
    }
    /**
     * 获取session
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            try {
                session = (SimpleSession) redisTemplate.opsForValue().get(SHIRO_SESSION + sessionId.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return session;
    }
    /**
     * 更新session的最后一次访问时间
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        try {
            redisTemplate.opsForValue().set(SHIRO_SESSION + session.getId().toString(), session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisTemplate.expire(SHIRO_SESSION + session.getId().toString(), SESSION_TIME, TimeUnit.SECONDS);
    }
    /**
     * 删除session
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        try {
            redisTemplate.delete(SHIRO_SESSION + session.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
