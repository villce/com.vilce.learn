package com.vilce.test.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.config.TestProxy
 * @Author: 雷才哲
 * @Date: 2020/5/12 14:26
 * @Version: 1.0
 */
public class TestProxy {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        //可以使用@Primary指定元素，或直接使用name名获取。
        //Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException:
        // No qualifying bean of type 'com.tellme.Impl.proxy.TService' available:
        // expected single matching bean but found 2: tService,proxyFactoryBean。（或生成两个Bean名字）
        //TService bean = applicationContext.getBean(TService.class);
        //若使用Class获取Bean，会获取到两个Bean对象。
        TService bean = (TService)applicationContext.getBean("proxyFactoryBean");
        bean.run1();
        System.out.println(bean.getClass());
    }
}
