package com.vilce.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 手机号码工具了
 * @Author 姚明洋
 * @Version: 1.0
 */
public class PhoneUtils {
    /**
     * @Description 隐藏手机号码中间四位
     * @Version  1.0
     */
    public static String hidden(String phone){
        if(StringUtils.isEmpty(phone)){
            return null;
        }
        //$1 $2 表示正则表达式里面的第一个和第二个，也就是括号里面的内容
        return StringUtils.replacePattern(phone,"(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static void main(String[] args) {
        Calendar start = Calendar.getInstance();
        Calendar date = (Calendar) start.clone();
        System.out.println(start.getTime());
        start.add( Calendar.DATE, -1);
        System.out.println(start.getTime());
        System.out.println(date.getTime());
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(2);
        System.out.println(a.max(b));
    }
}
