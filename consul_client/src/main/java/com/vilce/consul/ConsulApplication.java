package com.vilce.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.ConsulApplication
 * @Author: 雷才哲
 * @Date: 2020/9/14 15:59
 * @Version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
//@EnableCircuitBreaker
@EnableHystrix
public class ConsulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }
}
