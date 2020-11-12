package com.vilce.common.model.log.level;

import ch.qos.logback.classic.Level;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 日志级别
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.logback.level.LogLevel
 * @Author: 雷才哲
 * @Date: 2020/11/11 11:05
 * @Version: 1.0
 */
public class LogLevel {
    private static final List<String> levelArray = Arrays.asList(Level.ERROR.levelStr, Level.WARN.levelStr, Level.INFO.levelStr, Level.DEBUG.levelStr, Level.TRACE.levelStr, Level.ALL.levelStr);

    /**
     * 根据字符串获取对应日志级别（忽略字符串大小写）
     *
     * @param level 日志级别字符串
     * @return
     */
    public static Level getLogLevel(String level) {
        if (StringUtils.equalsIgnoreCase(level, Level.ERROR.levelStr)) {
            return Level.ERROR;
        } else if (StringUtils.equalsIgnoreCase(level, Level.WARN.levelStr)) {
            return Level.WARN;
        } else if (StringUtils.equalsIgnoreCase(level, Level.INFO.levelStr)) {
            return Level.INFO;
        } else if (StringUtils.equalsIgnoreCase(level, Level.DEBUG.levelStr)) {
            return Level.DEBUG;
        } else if (StringUtils.equalsIgnoreCase(level, Level.TRACE.levelStr)) {
            return Level.TRACE;
        } else {
            return Level.ALL;
        }
    }

    /**
     * 获取下一个日志级别，由上至下，最小为 ALL，ALL 的下一个也为 ALL
     * @param level
     * @return
     */
    public static Level getNextLevel(String level) {
        level = StringUtils.upperCase(level);
        if (levelArray.indexOf(level) + 1 < levelArray.size()) {
            level = levelArray.get(levelArray.indexOf(level) + 1);
        }
        return getLogLevel(level);
    }
}
