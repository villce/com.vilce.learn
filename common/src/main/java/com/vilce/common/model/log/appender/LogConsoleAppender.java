package com.vilce.common.model.log.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.core.ConsoleAppender;
import com.vilce.common.model.log.filter.LogFilter;
import com.vilce.common.model.log.po.LogInfo;

import java.nio.charset.Charset;

/**
 * @Description: 通过名称创建 appender（控制台用）
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.logback.appender.LogConsoleAppender
 * @Author: 雷才哲
 * @Date: 2020/11/11 16:59
 * @Version: 1.0
 */
public class LogConsoleAppender {
    /**
     * logger上下文
     */
    private LoggerContext loggerContext;
    /**
     * 日志信息
     */
    private LogInfo logInfo;

    public LogConsoleAppender(LoggerContext loggerContext, LogInfo logInfo) {
        this.loggerContext = loggerContext;
        this.logInfo = logInfo;
    }

    /**
     * 控制台打印appender
     *
     * @param level 日志级别
     * @return
     */
    public ConsoleAppender getConsoleAppender(Level level) {

        //这里是可以用来设置appender的，在xml配置文件里面，是这种形式：
        ConsoleAppender appender = new ConsoleAppender();

        //这里设置级别过滤器
        LogFilter logFilter = new LogFilter();
        ThresholdFilter levelFilter = logFilter.getThresholdFilter(level);
        levelFilter.start();

        //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
        // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
        appender.setContext(loggerContext);
        //appender的name属性
        appender.setName("CONSULE");

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
        // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
        encoder.setContext(loggerContext);
        //设置格式
        encoder.setPattern(logInfo.getCommonPattern());
        //设置编码格式
        encoder.setCharset(Charset.forName("UTF-8"));
        encoder.start();

        //添加过滤器
        appender.addFilter(levelFilter);
        //设置编码
        appender.setEncoder(encoder);
        appender.start();
        return appender;
    }
}
