package com.vilce.test.utils;

import java.util.Stack;

/**
 * @Description: 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 * @ProjectName: learn
 * @Package: com.vilce.test.utils
 * @Author: lcz
 * @Date: 2021/7/23 下午5:39
 * @Version: 1.0
 */
public class Subject5 {

    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        String s4 = "ac";
        System.out.println(longestPalindrome(s1));
        System.out.println(longestPalindrome(s2));
        System.out.println(longestPalindrome(s3));
        System.out.println(longestPalindrome(s4));
    }

    // 1. 回文字符串使用栈进行验证
    public static String longestPalindrome(String s) {
        Stack stack = new Stack();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Stack newStack = stack;

        }
    }
}
