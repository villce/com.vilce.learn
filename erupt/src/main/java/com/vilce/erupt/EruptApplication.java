package com.vilce.erupt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import xyz.erupt.core.annotation.EruptScan;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: PACKAGE_NAME
 * @Author: lcz
 * @Date: 2021/10/30 下午2:40
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan({"xyz.erupt", "com.vilce.erupt"})
@EntityScan({"xyz.erupt", "com.vilce.erupt"})
@EruptScan({"xyz.erupt", "com.vilce.erupt"})
public class EruptApplication {
    public static void main(String[] args) {
        SpringApplication.run(EruptApplication.class, args);
    }
}
