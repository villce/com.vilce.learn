package com.vilce.test;

import com.vilce.common.utils.HiddenUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.Application
 * @Author: 雷才哲
 * @Date: 2019/12/5 16:54
 * @Version: 1.0
 */
@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * 日志隐藏敏感字段
     */
//    @Bean
//    public void hidden(){
//        HiddenUtils.addHiddenRule();
//    }
}
