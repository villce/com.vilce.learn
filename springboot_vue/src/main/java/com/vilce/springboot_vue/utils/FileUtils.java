package com.vilce.springboot_vue.utils;

import java.util.Random;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.utils.FileUtils
 * @Author: 雷才哲
 * @Date: 2020/8/24 16:24
 * @Version: 1.0
 */
public class FileUtils {
    /**
     * 随机生成文件名
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
