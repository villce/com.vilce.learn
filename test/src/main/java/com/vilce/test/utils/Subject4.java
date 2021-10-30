package com.vilce.test.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * @ProjectName: learn
 * @Package: com.vilce.test.utils
 * @Author: lcz
 * @Date: 2021/7/19 下午4:17
 * @Version: 1.0
 */
public class Subject4 {

    public static void main(String[] args) {
        int[] a1 = {1, 3};
        int[] a2 = {2};
        int[] b1 = {1, 3};
        int[] b2 = {2, 7};
        int[] c = {0, 0};
        int[] d1 = {};
        int[] d2 = {1};
        int[] e1 = {2};
        int[] e2 = {};
//        System.out.println(findMedianSortedArrays2(a1, a2));
        System.out.println(findMedianSortedArrays2(b1, b2));
//        System.out.println(findMedianSortedArrays2(c, c));
//        System.out.println(findMedianSortedArrays2(d1, d2));
//        System.out.println(findMedianSortedArrays2(e1, e2));
    }

    // 1. 合并，排序，求中位
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums1) {
            list.add(i);
        }
        for (int i : nums2) {
            list.add(i);
        }
        // 排序
        Collections.sort(list);
        int len = list.size();
        if (len == 0) {
            return 0.0;
        } else if (len == 1) {
            return list.get(0);
        } else if (len % 2 == 0) {
            // 数量为偶数时，中位数为两数平均
            double sum = list.get(len / 2) + list.get(len / 2 - 1);
            return sum / 2;
        } else {
            // 数量为奇数时，中位数为中间数
            return list.get(len / 2);
        }
    }

    // 2. 首先获取中位数大概的长度，然后取值
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        // 保证nums1数比nums2少
        if (len1 > len2) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        // 计算中位数标
        int median = (len1 + len2 + 1) / 2;
        int left = 0, right = len1;
        while (left < right) {
            // num1中需要排除的数
            int i = left + (right - left + 1) / 2;
            // num2中需要排除的数
            int j = median - i;
            if (nums1[i - 1] > nums2[j]) {
                // 排除num2[j]之前的数
                right = i - 1;
            } else {
                left = i;
            }
        }
        // num1排除数据
        int i = left;
        // num2排除数据
        int j = median - i;
        int num1leftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int num1rightMin = i == len1 ? Integer.MAX_VALUE : nums1[i];
        int num2leftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int num2rightMin = j == len2 ? Integer.MAX_VALUE : nums2[j];
        // 此时的i，j为中位数的左标
        if ((len1 + len2) % 2 == 1) {
            // 为奇数时
            return Math.max(num1leftMax, num2leftMax);
        } else {
            // 为偶数时
            return (double) (Math.max(num1leftMax, num2leftMax) + Math.min(num1rightMin, num2rightMin)) / 2;
        }
    }
}
