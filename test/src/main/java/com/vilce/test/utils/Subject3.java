package com.vilce.test.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * @ProjectName: learn
 * @Package: com.vilce.test.utils
 * @Author: lcz
 * @Date: 2021/7/17 下午4:58
 * @Version: 1.0
 */
public class Subject3 {
    public static void main(String[] args) {
        String str1 = "abcabcbb";
        String str2 = "bbbbb";
        String str3 = "pwwkew";
        String str4 = "au";
        System.out.println(getLongStr2(str1));
        System.out.println(getLongStr2(str2));
        System.out.println(getLongStr2(str3));
        System.out.println(getLongStr2(str4));
    }

    // 1. 暴力破解，多重循环遍历
    public static int getLongStr(String s) {
        int len = 0;
        if (s.length() > 0 && s.trim().length() <= 1) {
            return 1;
        }
        for (int i = 0; i <= s.length() - 1; i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (j + 1 > s.length()) {
                    if (sub.length() > len) {
                        len = sub.length();
                    }
                } else {
                    String a = s.substring(j, j + 1);
                    if (sub.contains(a)) {
                        // 当字符重复时
                        if (sub.length() > len) {
                            len = sub.length();
                        }
                        break;
                    }
                }
            }
        }
        return len;
    }

    // 2. 滑动窗口
    public static int getLongStr2(String s) {
        int len = 0;
        if (s.length() > 0 && s.trim().length() <= 1) {
            return 1;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            // 当前末位划入的字符
            char chart = s.charAt(end);
            if (map.containsKey(chart)) {
                // 如果存在重复，窗口首部缩进
                start = Math.max(map.get(chart), start);
            }
            len = Math.max(len, end - start + 1);
            map.put(chart, end + 1);
        }
        return len;
    }
}
