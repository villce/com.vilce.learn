package com.vilce.test.config;

import com.vilce.test.model.MyTransaction;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.config.TService
 * @Author: 雷才哲
 * @Date: 2020/5/12 14:26
 * @Version: 1.0
 */
public class TService {
    @MyTransaction("解析自定义注解")
    public void run1() {
        System.out.println("This is a run1() Method!");
    }
    @Transactional
    public void say() {
        System.out.println("说话...");
    }
}
