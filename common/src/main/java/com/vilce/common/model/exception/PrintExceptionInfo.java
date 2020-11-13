package com.vilce.common.model.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 获取打印异常日志信息
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.exception.PrintExceptionInfo
 * @Author: 雷才哲
 * @Date: 2020/11/13 11:08
 * @Version: 1.0
 */
public class PrintExceptionInfo {
    /**
     * @Description 打印错误日志信息
     * @Version 1.0
     */
    public static String printErrorInfo(Throwable e) {
        String message = e.toString();
        StackTraceElement[] elements = e.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            StackTraceElement element = elements[i];
            if (i == 0) {
                message = StringUtils.join(element.toString(), " ", message);
            } else {
                message = StringUtils.join(message, "\n", element.toString());
            }
        }
        return message;
    }
}
