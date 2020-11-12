package com.vilce.common.autoconfig.log.api.even;

import ch.qos.logback.classic.Level;
import com.vilce.common.model.log.utils.LoggerUtils;
import com.vilce.common.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Objects;

/**
 * @Description: 日志事件监听器
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.api.even.LogApplicationListener
 * @Author: 雷才哲
 * @Date: 2020/11/12 14:00
 * @Version: 1.0
 */
public class LogApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (Objects.isNull(event.getSource())) {
            return;
        }
        if (event.getSource() instanceof LogAop) {
            LogAop logAop = (LogAop) event.getSource();
            if (StringUtils.equalsIgnoreCase(Level.INFO.levelStr, logAop.getLogLevel())) {
                if (LoggerUtils.isDebug()) {
                    LoggerUtils.info(logAop.getaClass(), JSONUtils.toJsonPretty(logAop.getLogInfo()));
                } else {
                    LoggerUtils.info(logAop.getaClass(), JSONUtils.toJson(logAop.getLogInfo()));
                }
            } else if (StringUtils.equalsIgnoreCase(Level.ERROR.levelStr, logAop.getLogLevel())) {
                if (LoggerUtils.isDebug()) {
                    LoggerUtils.error(logAop.getaClass(), JSONUtils.toJsonPretty(logAop.getLogInfo()));
                } else {
                    LoggerUtils.error(logAop.getaClass(), JSONUtils.toJson(logAop.getLogInfo()));
                }
            }
        }
    }
}
