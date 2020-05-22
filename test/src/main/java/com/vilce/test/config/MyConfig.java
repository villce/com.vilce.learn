package com.vilce.test.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.config.MyConfig
 * @Author: 雷才哲
 * @Date: 2020/5/12 14:25
 * @Version: 1.0
 */
@Configuration
public class MyConfig {
    @Bean
    public LogMethodBeforeAdvice logMethodBeforeAdvice() {
        return new LogMethodBeforeAdvice();
    }

    @Bean("tService")
    public TService tService() {
        return new TService();
    }

    @Bean("proxyFactoryBean")
    public ProxyFactoryBean proxyFactoryBean(TService tService) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //代理的目标对象，效果等效于setTargetSource()
        proxyFactoryBean.setTarget(tService);
        //设置`String[] interceptorNames`，以便后续初始化"拦截器链"。
        proxyFactoryBean.setInterceptorNames("logMethodBeforeAdvice");
        return proxyFactoryBean;
    }
}
