package com.vilce.common.autoconfig.exception.config;

import com.vilce.common.autoconfig.exception.handler.ExceptionAdviceHandler;
import com.vilce.common.utils.LoggerUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 异常自动化配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.exception.config.ExceptionAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/11/11 10:01
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties(ExceptionProperties.class)
@ConditionalOnProperty(prefix = "spring.vilce.exception", name = "enable", havingValue = "true", matchIfMissing = true)
public class ExceptionAutoConfiguration implements CommandLineRunner {

    @Bean
    public ExceptionAdviceHandler exceptionAdviceHandler(){
        return new ExceptionAdviceHandler();
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerUtils.info(ExceptionAutoConfiguration.class, "【自动化配置】---异常捕获配置完成...");
    }
}
