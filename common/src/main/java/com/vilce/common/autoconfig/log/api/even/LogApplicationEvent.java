package com.vilce.common.autoconfig.log.api.even;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: 日志事件
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.autoconfig.log.api.even.LogApplicationEvent
 * @Author: 雷才哲
 * @Date: 2020/11/12 14:00
 * @Version: 1.0
 */
public class LogApplicationEvent extends ApplicationEvent {
    public LogApplicationEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }
}
