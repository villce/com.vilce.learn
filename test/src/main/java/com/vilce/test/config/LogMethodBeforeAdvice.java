package com.vilce.test.config;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.config.LogMethodBeforeAdvice
 * @Author: 雷才哲
 * @Date: 2020/5/12 14:25
 * @Version: 1.0
 */
public class LogMethodBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("this is logMethodBeforeAdvice!");
    }
}
