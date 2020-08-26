package com.vilce.springboot_vue.config.web;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Description: 错误页配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.config.web.ErrorConfig
 * @Author: 雷才哲
 * @Date: 2020/8/24 14:45
 * @Version: 1.0
 */
@Component
public class ErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
        registry.addErrorPages(error404Page);
    }

}

