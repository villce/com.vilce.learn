package com.vilce.test.compute;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.compute.ComputeMedian
 * @Author: 雷才哲
 * @Date: 2020/8/14 18:19
 * @Version: 1.0
 */
public class ComputeMedian {
    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 5, 6};
        int[] b = {2, 4, 6, 8};
        //将其两等份平分
        System.out.println(computeMedian(a, b));
    }

    private static double computeMedian(int[] a, int[] b) {
        // 所谓中位数其实就是a\b总和长度中间大小的数
        int len = a.length + b.length;
        if (len % 2 != 0) {
            // 如果为奇数

        }
        return 0;
    }
}
