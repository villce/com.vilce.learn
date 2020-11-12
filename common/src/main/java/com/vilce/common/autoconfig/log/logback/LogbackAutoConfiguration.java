package com.vilce.common.autoconfig.log.logback;

import com.vilce.common.model.log.builder.LoggerBuilder;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Objects;

/**
 * @Description: logback自动化配置（默认关闭）
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.logback.LogAutoConfiguration
 * @Author: 雷才哲
 * @Date: 2020/11/11 10:55
 * @Version: 1.0
 */
@Order(0)
@Configuration
@EnableConfigurationProperties(LogbackProperties.class)
@ConditionalOnProperty(prefix = "spring.vilce.logback", name = "enable", havingValue = "true")
public class LogbackAutoConfiguration implements CommandLineRunner {

    private LoggerBuilder loggerBuilder;

    @Bean
    @ConditionalOnMissingBean(LoggerBuilder.class)
    public LoggerBuilder defaultLogger(LogbackProperties logbackProperties) {
        loggerBuilder = new LoggerBuilder(logbackProperties);
        return loggerBuilder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (Objects.nonNull(loggerBuilder)) {
            LoggerUtils.setBuilder(loggerBuilder);
            LoggerUtils.info(LogbackAutoConfiguration.class, "【自动化配置】---logback日志组件初始化完成...");
        }
    }
}
