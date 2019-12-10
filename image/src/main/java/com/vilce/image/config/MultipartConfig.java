package com.vilce.image.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.image.config.MultipartConfig
 * @Author: 雷才哲
 * @Date: 2019/12/9 15:27
 * @Version: 1.0
 */
@Configuration
public class MultipartConfig {
    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大 KB,MB
        factory.setMaxFileSize(DataSize.parse("2MB"));
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("2MB"));
        return factory.createMultipartConfig();
    }
}
