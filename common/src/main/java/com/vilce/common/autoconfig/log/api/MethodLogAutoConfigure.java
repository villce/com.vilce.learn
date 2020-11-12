package com.vilce.common.autoconfig.log.api;

import com.vilce.common.autoconfig.log.api.even.LogApplicationListener;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

/**
 * @Description: API日志拦截自动化配置（默认开启）
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.api.MethodLogAutoConfigure
 * @Author: 雷才哲
 * @Date: 2020/9/3 10:10
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties(MethodLogProperties.class)
@Import(LogApplicationListener.class)
@ConditionalOnProperty(prefix = "spring.vilce.log", name = "enable", havingValue = "true", matchIfMissing = true)
public class MethodLogAutoConfigure implements CommandLineRunner {

    @Autowired
    private MethodLogProperties properties;
    /**
     * 在多个表达式之间使用  || , or 表示  或 ，使用  && , and 表示  与 ， ！ 表示 非
     */
    private static final String DEFAULT_POINT_CUT = StringUtils.join("@annotation(org.springframework.web.bind.annotation.GetMapping) ",
            "or @annotation(org.springframework.web.bind.annotation.PostMapping) ",
            "or @annotation(org.springframework.web.bind.annotation.PutMapping) ",
            "or @annotation(org.springframework.web.bind.annotation.DeleteMapping) ",
            "or @annotation(org.springframework.web.bind.annotation.RequestMapping) ");

    @Bean
    @ConditionalOnClass(value = {MethodLogInterceptor.class, ApplicationEventPublisher.class})
    public DefaultPointcutAdvisor defaultAdviceInterceptor(ApplicationEventPublisher eventPublisher) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //获取切面表达式
        pointcut.setExpression(DEFAULT_POINT_CUT);
        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new MethodLogInterceptor(eventPublisher));
        return advisor;
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerUtils.setDebug(properties.isDebug());
        LoggerUtils.info(MethodLogAutoConfigure.class, "【自动化配置】---API日志组件初始化完成...");
    }
}
