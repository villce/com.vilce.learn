package com.vilce.springboot_vue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.VueApplication
 * @Author: 雷才哲
 * @Date: 2019/12/19 17:55
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = "com.vilce")
@MapperScan(basePackages = "com.vilce.springboot_vue.mapper")
public class VueApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueApplication.class);
    }
}
