package com.vilce.common.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.async.config.thread.ThreadConfig
 * @Author: 雷才哲
 * @Date: 2019/12/5 14:31
 * @Version: 1.0
 */
@Configuration
public class ThreadConfig {

    @Bean("asyncExecutor")
    public Executor asyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数为10：线程池创建时初始化的线程数
        executor.setCorePoolSize(10);
        //最大线程数为20：只有缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(20);
        //缓冲队列数为200：用来缓冲执行任务的队列
        executor.setQueueCapacity(200);
        //空闲时间为60：超过核心线程之外的线程空闲时间超过预设值时会被销毁
        executor.setKeepAliveSeconds(60);
        //线程池名的前缀，方便定位
        executor.setThreadNamePrefix("asyncExecutor1-");
        //线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，
        //该策略会直接在execute方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
