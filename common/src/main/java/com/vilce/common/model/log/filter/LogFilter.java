package com.vilce.common.model.log.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.filter.ThresholdFilter;

import static ch.qos.logback.core.spi.FilterReply.ACCEPT;
import static ch.qos.logback.core.spi.FilterReply.DENY;

/**
 * @Description: 日志过滤器
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.logback.filter.LogFilter
 * @Author: 雷才哲
 * @Date: 2020/11/11 16:14
 * @Version: 1.0
 */
public class LogFilter {

    /**
     * 根据 level 设置过滤器
     */
    public LevelFilter getLevelFilter(Level level){
        LevelFilter levelFilter = new LevelFilter();
        levelFilter.setLevel(level);
        levelFilter.setOnMatch(ACCEPT);
        levelFilter.setOnMismatch(DENY);
        return levelFilter;
    }

    /**
     * 根据 level 设置控制台过滤器
     */
    public ThresholdFilter getThresholdFilter(Level level){
        ThresholdFilter thresholdFilter = new ThresholdFilter();
        thresholdFilter.setLevel(level.levelStr);
        return thresholdFilter;
    }

}
