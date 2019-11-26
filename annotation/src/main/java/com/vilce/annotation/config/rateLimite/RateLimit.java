package com.vilce.annotation.config.rateLimite;

import java.lang.annotation.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.config.rateLimite.RateLimit
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
}
