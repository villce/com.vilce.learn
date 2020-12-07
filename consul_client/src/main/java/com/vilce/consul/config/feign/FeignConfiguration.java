package com.vilce.consul.config.feign;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.config.feign.FeignConfiguration
 * @Author: 雷才哲
 * @Date: 2020/12/4 9:59
 * @Version: 1.0
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
    }
}
