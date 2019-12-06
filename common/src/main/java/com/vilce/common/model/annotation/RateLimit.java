package com.vilce.common.model.annotation;

import com.vilce.common.model.enums.TimeEnum;

import java.lang.annotation.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.config.aop.RateLimit
 * @Author: 雷才哲
 * @Date: 2019/11/25 16:22
 * @Version: 1.0
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    //默认每秒放入桶中的token
    double limitNum() default 20;

    //限制字段
    String limitName();

    //限制时间单位
    TimeEnum timeUnit();
}
