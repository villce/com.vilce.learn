package com.vilce.actuator.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.server.ActuatorServerApplication
 * @Author: 雷才哲
 * @Date: 2020/6/22 10:04
 * @Version: 1.0
 */
@SpringBootApplication
@EnableAdminServer
public class ActuatorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorServerApplication.class);
    }
}
