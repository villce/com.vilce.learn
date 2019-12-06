package com.vilce.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.Application
 * @Author: 雷才哲
 * @Date: 2019/12/5 16:54
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = "com.vilce")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
