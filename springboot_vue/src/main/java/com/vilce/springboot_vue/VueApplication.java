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
// todo 无法加载 common 中实现的自动配置，需要优化：添加扫描包会报错，不添加无法使用自动配置
@SpringBootApplication
@MapperScan(basePackages = "com.vilce.springboot_vue.mapper")
public class VueApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueApplication.class);
    }
}
