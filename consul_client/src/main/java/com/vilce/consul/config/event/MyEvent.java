package com.vilce.consul.config.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.config.event.MyEvent
 * @Author: 雷才哲
 * @Date: 2020/12/24 15:31
 * @Version: 1.0
 */
public class MyEvent extends ApplicationEvent {

    private boolean result;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyEvent(Object source, boolean result) {
        super(source);
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
