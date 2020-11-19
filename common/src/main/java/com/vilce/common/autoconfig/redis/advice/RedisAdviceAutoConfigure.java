package com.vilce.common.autoconfig.redis.advice;

import com.vilce.common.autoconfig.redis.RedisAutoConfiguration;
import com.vilce.common.autoconfig.redis.RedisProperties;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.redis.advice.RedisAdviceAutoConfigure
 * @Author: 雷才哲
 * @Date: 2020/11/19 19:41
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnProperty(prefix = "spring.vilce.redis.advice", name = "enable", havingValue = "true", matchIfMissing = true)
public class RedisAdviceAutoConfigure implements CommandLineRunner {
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    @ConditionalOnClass(value = {RedisAdviceInterceptor.class})
    public DefaultPointcutAdvisor redisAdviceInterceptor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //获取切面表达式
        pointcut.setExpression("@annotation(com.vilce.common.autoconfig.redis.annotation.RedisFilter)");
        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new RedisAdviceInterceptor(redisTemplate));
        return advisor;
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerUtils.info(RedisAutoConfiguration.class, "【自动化配置】---Redis拦截初始化完成...");
    }
}
