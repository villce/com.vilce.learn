package com.vilce.test.config;

import com.vilce.test.model.MyTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.config.TService
 * @Author: 雷才哲
 * @Date: 2020/5/12 14:26
 * @Version: 1.0
 */
public class TService {
    @MyTransaction("解析自定义注解")
    public void run1() {
        System.out.println("This is a run1() Method!");
    }

    @Transactional
    public void say() {
        System.out.println("说话...");
    }

    /**
     * 快排获取排序数组
     *
     * @param num
     * @return
     */
    public static void getSort(int[] num, int low, int high) {
        if (low > high) {
            return;
        }
        //获取一个对比基准
        int a = num[low];
        //获取最低最高索引
        int i = low;
        int j = high;
        //遍历
        while (i < j) {
            //从右往左，寻找比基准数小的值
            while (i < j && num[j] > a) {
                j--;
            }
            //从左往右，寻找比基准数大的值
            while (i < j && num[i] <= a) {
                i++;
            }
            int tmp = num[j];
            num[j] = num[i];
            num[i] = tmp;
        }
        //遍历完后，将基准与low处的值进行交换
        int b = num[i];
        num[i] = num[low];
        num[low] = b;
        getSort(num, low, i - 1);
        getSort(num, i + 1, high);
    }

    public static void main(String[] args) {
        int[] num = {49, 38, 65, 76, 1, 8, 49, 0, -1, 22};
        System.out.println(Arrays.toString(num));
        quickSort(num, 0, num.length - 1);
        System.out.println(Arrays.toString(num));
    }

    public static void quickSort(int[] num, int low, int high) {
        if (low < high) {
            //获取每次迭代的基准数索引
            int index = getIndex(num, low, high);
            quickSort(num, low, index-1);
            quickSort(num, index+1, high);
        }
    }

    public static int getIndex(int[] num, int low, int high) {
        //获取基准数
        int tmp = num[low];
        while (low < high) {
            //从右往左，寻找小于基准的值
            while (low < high && num[high] >= tmp) {
                high--;
            }
            //将小于基准的值与基准索引处的值交换
            num[low] = num[high];
            //从左往右，寻找大于基准的值
            while (low < high && num[low] <= tmp) {
                low++;
            }
            //将大于基准的值与high的值交换
            num[high] = num[low];
        }
        //此时将low处的值置换为基准值
        num[low] = tmp;
        return low;
    }
}
