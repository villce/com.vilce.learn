package com.vilce.common.model.log.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import com.vilce.common.model.log.filter.LogFilter;
import com.vilce.common.model.log.level.LogLevel;
import com.vilce.common.model.log.po.LogInfo;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

/**
 * @Description: 通过名称创建 appender
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.logback.appender.LogRollingFileAppender
 * @Author: 雷才哲
 * @Date: 2020/11/11 15:56
 * @Version: 1.0
 */
public class LogRollingFileAppender {

    /**
     * logback 上下文
     */
    private LoggerContext loggerContext;
    /**
     * logback 信息
     */
    private LogInfo logInfo;

    public LogRollingFileAppender(LoggerContext loggerContext, LogInfo logInfo) {
        this.loggerContext = loggerContext;
        this.logInfo = logInfo;
    }

    /**
     * 按时间规定文件，获取对应指定名称、路径、等级的 appender 附加器
     *
     * @param name     appender属性name
     * @param path     路径
     * @param fileName 文件名
     * @param level    过滤日志级别
     * @return
     */
    public RollingFileAppender getRollingFileAppender(String name, String path, String fileName, Level level) {
        // 这里是可以用来设置 appender 的，在 xml 配置文件里面，是这种形式：
        // <appender name="error" class="ch.qos.logback.core.rolling.RollingFileAppender">
        RollingFileAppender appender = new RollingFileAppender();
        // ConsoleAppender consoleAppender = new ConsoleAppender();

        // 这里设置级别过滤器
        LogFilter logFilter = new LogFilter();
        LevelFilter levelFilter = logFilter.getLevelFilter(level);
        levelFilter.start();

        if (logInfo.isEnableSizeAndTimeRollingPolicy() && level.levelInt >= LogLevel.getNextLevel(logInfo.getLevel()).levelInt) {
            //文件归档大小和时间设置
            SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();
            //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
            // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
            policy.setContext(loggerContext);
            /**
             * 归档文件名格式设置
             * 将文件名及路径字符串编译为字符串
             * http://www.logback.cn/04%E7%AC%AC%E5%9B%9B%E7%AB%A0Appenders.html
             * /info/foo.%d 每天归档
             * /info/%d{yyyy/MM}/foo.txt 每个月开始的时候归档
             * /info/foo.%d{yyyy-ww}.logback 每个周的第一天开始归档
             * /info/foo%d{yyyy-MM-dd_HH}.logback 每小时归档
             * /info/foo%d{yyyy-MM-dd_HH-mm}.logback 每分钟归档
             * /info/info.%d 每天轮转
             */
            // 文件名格式
            String fp = OptionHelper.substVars(StringUtils.join(logInfo.getPath(), "/", path, "/", fileName, ".%d{yyyy-MM-dd}.%i.logback"), loggerContext);
            // 设置文件名模式
            policy.setFileNamePattern(fp);
            //最大日志文件大小 KB,MB,GB
            if (StringUtils.isNotEmpty(logInfo.getMaxFileSize())) {
                policy.setMaxFileSize(FileSize.valueOf(logInfo.getMaxFileSize()));
            }
            //设置要保留的最大存档文件数
            policy.setMaxHistory(logInfo.getMaxHistory());
            //文件总大小限制 KB,MB,G
            if (StringUtils.isNotEmpty(logInfo.getTotalSizeCap())) {
                policy.setTotalSizeCap(FileSize.valueOf(logInfo.getTotalSizeCap()));
            }
            // 设置父节点是 appender
            policy.setParent(appender);
            policy.start();
            //设置文件归档策略
            appender.setRollingPolicy(policy);
        } else {
            //文件归档大小和时间设置
            TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
            //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
            // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
            policy.setContext(loggerContext);
            /**
             * 归档文件名格式设置
             * 将文件名及路径字符串编译为字符串
             * http://www.logback.cn/04%E7%AC%AC%E5%9B%9B%E7%AB%A0Appenders.html
             * /info/foo.%d 每天归档
             * /info/%d{yyyy/MM}/foo.txt 每个月开始的时候归档
             * /info/foo.%d{yyyy-ww}.logback 每个周的第一天开始归档
             * /info/foo%d{yyyy-MM-dd_HH}.logback 每小时归档
             * /info/foo%d{yyyy-MM-dd_HH-mm}.logback 每分钟归档
             * /info/info.%d 每天轮转
             */
            String fp = OptionHelper.substVars(StringUtils.join(logInfo.getPath(), "/", path, "/", fileName, "%d{yyyy-MM-dd}.log"), loggerContext);
            //设置文件名模式
            policy.setFileNamePattern(fp);
            //设置要保留的最大存档文件数
            policy.setMaxHistory(logInfo.getMaxHistory());
            //设置父节点是appender
            policy.setParent(appender);
            policy.start();
            //设置文件归档策略
            appender.setRollingPolicy(policy);
        }

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        // 设置上下文，每个 logger 都关联到 logger 上下文，默认上下文名称为 default
        // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改
        encoder.setContext(loggerContext);
        //设置格式
        if (StringUtils.equalsIgnoreCase(fileName, level.levelStr)) {
            encoder.setPattern(logInfo.getCommonPattern());
        } else {
            encoder.setPattern(logInfo.getModulePattern());
        }
        //设置编码格式
        encoder.setCharset(Charset.forName("UTF-8"));
        encoder.start();

        //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
        // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
        appender.setContext(loggerContext);
        //appender的name属性
        appender.setName(name);
        //设置文件名
        appender.setFile(OptionHelper.substVars(StringUtils.join(logInfo.getPath(), "/", path, "/", fileName, ".log"), loggerContext));
        //如果是 true，日志被追加到文件结尾，如果是 false，清空现存文件，默认是true
        appender.setAppend(true);
        //如果是 true，日志会被安全的写入文件，即使其他的FileAppender也在向此文件做写入操作，效率低，默认是 false
        appender.setPrudent(false);
        //设置过滤器
        appender.addFilter(levelFilter);

        //设置附加器编码
        appender.setEncoder(encoder);
        appender.start();

        return appender;
    }
}
