package com.vilce.springboot_vue.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: description
 * @ProjectName: operate
 * @Package: com.eastmoney.emis.operate.config.shiro
 * @Author: 雷才哲
 * @Date: 2021/3/22 下午2:28
 * @Version: 1.0
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public SimpleCookie getSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("SHRIOSESSIONID");
        return simpleCookie;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 自定义过滤器设置 1
        Map<String, Filter> customizedFilter = new HashMap<>();
        // 自定义过滤器设置 2，命名，需在设置过滤路径前
        // customizedFilter.put("url", getURLPathMatchingFilter());
        // 防鸡贼登录
        // filterChainDefinitionMap.put("/user/info", "authc");
        // 自定义过滤器设置 3，设置过滤路径
        // filterChainDefinitionMap.put("/openAccount/**", "url");
        // 自定义过滤器设置 4，启用
        shiroFilterFactoryBean.setFilters(customizedFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置权限验证器
     *
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm wjRealm = new ShiroRealm();
        wjRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return wjRealm;
    }

    /**
     * 配置缓存验证器
     *
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

    /**
     * 配置记住Cookie对象参数
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name=rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间为60秒
        simpleCookie.setMaxAge(60);
        return simpleCookie;
    }

    /**
     * 配置Cookie管理对象
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("EVANNIGHTLY_WAOU".getBytes());
        return cookieRememberMeManager;
    }

    /**
     * 配置securityManager安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //注入自定义myRealm
        defaultWebSecurityManager.setRealm(shiroRealm());
        //注入自定义cacheManager
        // defaultWebSecurityManager.setCacheManager(redisCacheManager());
        //注入记住我管理器
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        //注入自定义sessionManager
        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        return redisSessionDAO;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        //session有效期 默认值1800000 30分钟 1800000毫秒  -1000表示永久
        sessionManager.setGlobalSessionTimeout(-1000);
        SimpleCookie simpleCookie = getSimpleCookie();
        //设置js不可读取此Cookie
        simpleCookie.setHttpOnly(true);
        //3年 cookie有效期
        simpleCookie.setMaxAge(3 * 365 * 24 * 60 * 60);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    /**
     * 配置shiro注解生效
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 配置shiro的生命周期
     *
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
