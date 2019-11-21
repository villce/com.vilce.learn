package com.vilce.swagger.config.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.AsyncConfig
 * @Author: 雷才哲
 * @Date: 2019/11/11 10:26
 * @Version: 1.0
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    /**
     * 定义线程池
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(12);
        // 线程池最大线程数
        taskExecutor.setMaxPoolSize(32);
        // 线程队列最大线程数
        taskExecutor.setQueueCapacity(4000);
        // 初始化
        taskExecutor.initialize();
        return taskExecutor;
    }
}
