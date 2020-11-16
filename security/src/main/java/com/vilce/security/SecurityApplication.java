package com.vilce.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.SecurityApplication
 * @Author: 雷才哲
 * @Date: 2019/12/10 11:28
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.vilce.security.repository")
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class);
    }
}
