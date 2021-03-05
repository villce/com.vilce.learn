package com.vilce.common.utils;

import com.vilce.common.model.enums.DateEnum;
import com.vilce.common.model.log.utils.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @Description: 时间转换工具
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.TimeUtils
 * @Author: 雷才哲
 * @Date: 2019/12/20 10:42
 * @Version: 1.0
 */
public class TimeUtils {

    /**
     * yyyy/MM/dd HH:mm:ss转换为yyyy.MM格式显示
     * @param time
     * @return
     */
    public static String yearMonthSpot(String time){
        try {
            return DateFormatUtils.format(DateUtils.parseDate(time,
                    "yyyy/MM/dd HH:mm:ss"), DateEnum.YYYY_MM_SPOT.getFormat());
        }catch (Exception e){
             e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,time+"转换失败");
            return "--";
        }
    }

    /**
     * yyyy/MM/dd HH:mm:ss转换为yyyy_MM格式显示
     * @param time
     * @return
     */
    public static String yearMonth(String time){
        try {
            return DateFormatUtils.format(DateUtils.parseDate(time,
                    "yyyy/MM/dd HH:mm:ss"), DateEnum.YYYY_MM.getFormat());
        }catch (Exception e){
             e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,time+"转换失败");
            return "--";
        }
    }

    /**
     * yyyyMMdd转换为MM月dd日格式显示
     * @param time
     * @return
     */
    public static String ymdToMonthDayCN(String time){
        try {
            return DateFormatUtils.format(DateUtils.parseDate(time,
                    "yyyyMMdd"), DateEnum.MM_DD_CN.getFormat());
        }catch (Exception e){
             e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,time+"转换失败");
            return "--";
        }
    }

    /**
     * yyyyMMddHHmmss转换为MM月dd日格式显示
     * @param time
     * @return
     */
    public static String ymdhmsToMonthDayCN(String time){
        try {
            return DateFormatUtils.format(DateUtils.parseDate(time,
                    "yyyyMMddHHmmss"), DateEnum.MM_DD_CN.getFormat());
        }catch (Exception e){
             e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,time+"转换失败");
            return "--";
        }
    }

    /**
     * yyyy/MM/dd HH:mm:ss转换为yyyy年MM月dd日格式显示
     * @param time
     * @return
     */
    public static String yearMonthDayCN(String time){
        try {
            return DateFormatUtils.format(DateUtils.parseDate(time,
                    "yyyy/MM/dd HH:mm:ss"), DateEnum.YYYY_MM_DD_CN.getFormat());
        }catch (Exception e){
             e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,time+"转换失败");
            return "--";
        }
    }

    /**
     * Date类型数据转换为yyyy-MM-dd格式
     * @param date
     * @return
     */
    public static String dateToYMD(Date date){
        try {
            String time = DateFormatUtils.format(date,DateEnum.YYYY_MM_DD.getFormat());
            return time;
        }catch (Exception e){
            e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,date+"转换失败");
            return "--";
        }
    }

    /**
     * Date类型数据转换为yyyy-MM-dd HH:mm:ss格式
     * @param date
     * @return
     */
    public static String dateToYMDHMS(Date date){
        try {
            String time = DateFormatUtils.format(date,DateEnum.YYYY_MM_DD_HH_MM_SS.getFormat());
            return time;
        }catch (Exception e){
             e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,date+"转换失败");
            return "--";
        }
    }

    /**
     * Date类型数据转换为HH:mm格式
     * @param date
     * @return
     */
    public static String dateToMS(Date date){
        try {
            String time = DateFormatUtils.format(date,DateEnum.HH_MM.getFormat());
            return time;
        }catch (Exception e){
             e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,date+"转换失败");
            return "--";
        }
    }

    /**
     * Date类型数据转换为yyyy年MM月dd日格式
     * @param date
     * @return
     */
    public static String dateToYMDayCN(Date date){
        try {
            String time = DateFormatUtils.format(date,DateEnum.YYYY_MM_DD_CN.getFormat());
            return time;
        }catch (Exception e){
            e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,date+"转换失败");
            return "--";
        }
    }

    /**
     * 判断time是否是今天
     * @param time
     * @return
     */
    public static Boolean isToday(Date time){
        try {
            String today = DateFormatUtils.format(new Date(),DateEnum.YYYY_MM_DD.getFormat());
            String dataTime = DateFormatUtils.format(time,
                    DateEnum.YYYY_MM_DD.getFormat());
            if (today.equals(dataTime)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
             e.printStackTrace();
            LoggerUtils.error(TimeUtils.class,time+"转换失败");
            return false;
        }
    }

    /**
     * 字符串日期格式化
     *
     * @param dateStr        字符串日期
     * @param originalFormat 原始日期格式
     * @return 格式化后的日期
     */
    public static Date formatDate(String dateStr, String originalFormat) {
        if (StringUtils.isNotEmpty(dateStr)) {
            try {
                Date date = DateUtils.parseDate(dateStr, originalFormat);
                return date;
            } catch (ParseException e) {
                LoggerUtils.error(TimeUtils.class, "日期格式转换异常，" + e);
            }
        }
        return null;
    }
}
