package com.vilce.springboot_vue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.VueApplication
 * @Author: 雷才哲
 * @Date: 2019/12/19 17:55
 * @Version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class VueApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueApplication.class);
    }
}
