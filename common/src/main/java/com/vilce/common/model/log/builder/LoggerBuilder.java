package com.vilce.common.model.log.builder;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.vilce.common.model.log.appender.LogConsoleAppender;
import com.vilce.common.model.log.appender.LogRollingFileAppender;
import com.vilce.common.model.log.level.LogLevel;
import com.vilce.common.model.log.po.LogInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 日志类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.logback.builder.LoggerBuilder
 * @Author: 雷才哲
 * @Date: 2020/11/11 16:01
 * @Version: 1.0
 */
public class LoggerBuilder {
    /**
     * Logger对象容器
     */
    private Map<String, Logger> loggerCache;
    /**
     * 日志信息
     */
    private LogInfo logInfo;

    public LoggerBuilder(LogInfo logInfo) {
        this.loggerCache = new ConcurrentHashMap<>();
        this.logInfo = logInfo;
    }

    /**
     * 获取日志输出对象
     *
     * @return
     */
    public Logger getLogger(Class<?> clazz) {
        return getLogger(clazz, null, LogInfo.DEFAULT_MODULE);
    }

    /**
     * 获取日志输出对象
     *
     * @param fileName 日志文件名|模块名称
     * @return
     */
    public Logger getLogger(Class<?> cls, String path, String fileName) {
        /**
         * 判定是否是默认文件名
         */
        boolean defaultBool = StringUtils.equals(LogInfo.DEFAULT_MODULE, fileName);
        if (defaultBool) {
            fileName = cls.getName();
        }
        Logger logger = loggerCache.get(fileName);
        if (Objects.nonNull(logger)) {
            return logger;
        }
        synchronized (LoggerBuilder.class) {
            logger = loggerCache.get(fileName);
            if (Objects.nonNull(logger)) {
                return logger;
            }
            if (defaultBool) {
                logger = builder(cls);
                loggerCache.put(cls.getName(), logger);
            } else {
                logger = builder(cls, path, fileName);
                loggerCache.put(fileName, logger);
            }
        }
        return logger;

    }

    /**
     * 构建Logger对象
     * 日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE >ALL
     *
     * @return
     */
    private Logger builder(Class<?> cls) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = loggerContext.getLogger(cls.getName());

        //设置是否向上级打印信息
        logger.setAdditive(false);
        logger.addAppender(new LogRollingFileAppender(loggerContext, logInfo).getRollingFileAppender(cls.getName(), StringUtils.lowerCase(Level.ERROR.levelStr), StringUtils.lowerCase(Level.ERROR.levelStr), Level.ERROR));
        logger.addAppender(new LogRollingFileAppender(loggerContext, logInfo).getRollingFileAppender(cls.getName(), StringUtils.lowerCase(Level.WARN.levelStr), StringUtils.lowerCase(Level.WARN.levelStr), Level.WARN));
        logger.addAppender(new LogRollingFileAppender(loggerContext, logInfo).getRollingFileAppender(cls.getName(), StringUtils.lowerCase(Level.INFO.levelStr), StringUtils.lowerCase(Level.INFO.levelStr), Level.INFO));
        logger.addAppender(new LogRollingFileAppender(loggerContext, logInfo).getRollingFileAppender(cls.getName(), StringUtils.lowerCase(Level.DEBUG.levelStr), StringUtils.lowerCase(Level.DEBUG.levelStr), Level.DEBUG));
        logger.addAppender(new LogRollingFileAppender(loggerContext, logInfo).getRollingFileAppender(cls.getName(), StringUtils.lowerCase(Level.TRACE.levelStr), StringUtils.lowerCase(Level.TRACE.levelStr), Level.TRACE));
        logger.addAppender(new LogRollingFileAppender(loggerContext, logInfo).getRollingFileAppender(cls.getName(), StringUtils.lowerCase(Level.ALL.levelStr), StringUtils.lowerCase(Level.ALL.levelStr), Level.ALL));
        logger.addAppender(new LogConsoleAppender(loggerContext, logInfo).getConsoleAppender(LogLevel.getLogLevel(logInfo.getLevel())));

        logger.setLevel(LogLevel.getLogLevel(logInfo.getLevel()));
        return logger;
    }

    /**
     * 构建Logger对象
     * 日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE >ALL
     *
     * @param fileName 日志文件名|模块名称
     * @return
     */
    private Logger builder(Class<?> cls, String path, String fileName) {
        //logger 属性namge名称
        String name = StringUtils.join(cls.getName(), "_", fileName);
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = loggerContext.getLogger(name);
        /**
         * 设置是否向上级打印信息
         */
        logger.setAdditive(false);
        logger.addAppender(new LogRollingFileAppender(loggerContext, logInfo).getRollingFileAppender(name, path, fileName, Level.INFO));
        if(logInfo.isEnableModuleConsule()){
            logger.addAppender(new LogConsoleAppender(loggerContext, logInfo).getConsoleAppender(LogLevel.getLogLevel(logInfo.getLevel())));
        }

        logger.setLevel(LogLevel.getLogLevel(logInfo.getLevel()));
        return logger;
    }
}
