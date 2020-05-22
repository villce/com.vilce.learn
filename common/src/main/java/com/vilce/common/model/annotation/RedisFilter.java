package com.vilce.common.model.annotation;

import java.lang.annotation.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.annotation.RedisFilter
 * @Author: 雷才哲
 * @Date: 2020/3/19 15:25
 * @Version: 1.0
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisFilter {
    //缓存基础Key值
    String basekKey();

    //缓存Request中Key值
    String requestKey();
}
