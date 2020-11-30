package com.vilce.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.RegexUtils
 * @Author: 雷才哲
 * @Date: 2020/11/28 10:43
 * @Version: 1.0
 */
public class RegexUtils {

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     *
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap, String regex) {
        List<String> list = new ArrayList<String>();
        // 匹配的模式
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            if (!m.group().equals(regex)) {
                int i = 1;
                list.add(m.group(i));
                i++;
            }
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     *
     * @param soap
     * @param regex
     * @return
     */
    public static String getSubUtilSimple(String soap, String regex) {
        // 匹配的模式
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            if (!m.group().equals(regex)) {
                return m.group(1);
            }
        }
        return "";
    }
}
