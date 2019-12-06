package com.vilce.common.utils;

import org.slf4j.LoggerFactory;

/**
 * @Description: 日志工具
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.LoggerUtils
 * @Author: 雷才哲
 * @Date: 2019/12/5 15:30
 * @Version: 1.0
 */
public class LoggerUtils {
    public static <T> void info(Class<T> clazz, String msg){
        LoggerFactory.getLogger(clazz).info(msg);
    }

    public static <T> void warn(Class<T> clazz, String msg){
        LoggerFactory.getLogger(clazz).warn(msg);
    }

    public static <T> void debug(Class<T> clazz, String msg){
        LoggerFactory.getLogger(clazz).debug(msg);
    }

    public static <T> void error(Class<T> clazz, String msg){
        LoggerFactory.getLogger(clazz).error(msg);
    }
}
