package com.vilce.springboot_vue.config.web;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.*;


/**
 * @Description: 跨域请求支持
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.config.web.WebConfiguration
 * @Author: 雷才哲
 * @Date: 2020/8/24 14:45
 * @Version: 1.0
 */
@SpringBootConfiguration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域，使用这种配置方法就不能在 interceptor 中再配置 header 了
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/file/**").addResourceLocations("file:" + "d:/img/");
    }
}
