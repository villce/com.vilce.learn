package com.vilce.common.autoconfig.log.api.service;

import com.vilce.common.autoconfig.log.api.po.LogAop;

/**
 * @Description: Description
 * @ProjectName: com.eastmoney.emis.utils
 * @Package: com.eastmoney.emis.autoconfigure.aop.log.service.LogAopService
 * @Author: 雷才哲
 * @Date: 2020/11/27 14:01
 * @Version: 1.0
 */
public interface LogAopService {

    /**
     * 日志信息初始化
     * @return
     */
    LogAop initLogAop();

    /**
     * @Description 记录请求信息
     * @Version 1.0
     */
    void logInfo(LogAop logAop);

    /**
     * @Description 记录响应信息
     * @Version 1.0
     */
    void logError(LogAop logAop);
}
