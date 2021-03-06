//package com.vilce.springboot_vue.config.web;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.web.servlet.config.annotation.*;
//
//
///**
// * @Description: 访问路径映射
// * @ProjectName: com.vilce.learn
// * @Package: com.vilce.springboot_vue.config.web.WebConfiguration
// * @Author: 雷才哲
// * @Date: 2020/8/24 14:45
// * @Version: 1.0
// */
//@Configurable
//public class WebConfiguration implements WebMvcConfigurer {
//
//    @Value("${covers.url}")
//    private String coversUrl;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/image/file/**").addResourceLocations("file:" + coversUrl);
//    }
//}
