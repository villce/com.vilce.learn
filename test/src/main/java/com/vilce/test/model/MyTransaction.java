package com.vilce.test.model;

import java.lang.annotation.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.model.MyTransaction
 * @Author: 雷才哲
 * @Date: 2020/5/13 10:50
 * @Version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyTransaction {
    String value() default "";
}
