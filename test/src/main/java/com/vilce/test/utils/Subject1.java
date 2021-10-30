package com.vilce.test.utils;

import com.vilce.common.utils.JSONUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * @ProjectName: learn
 * @Package: com.vilce.test.utils
 * @Author: lcz
 * @Date: 2021/7/16 下午5:42
 * @Version: 1.0
 */
public class Subject1 {

    public static void main(String[] args) {
        int[] ints1 = {2, 7, 11, 15};
        int[] ints2 = {3, 2, 4};
        int[] ints3 = {3, 3};
        int[] ints4 = {1, 2, 3, 4, 5, 9, 11, 23, 34, 35, 36, 39};
        System.out.println(JSONUtils.toJSONString(sumTargetN3(ints1, 9)));
        System.out.println(JSONUtils.toJSONString(sumTargetN3(ints2, 6)));
        System.out.println(JSONUtils.toJSONString(sumTargetN3(ints3, 6)));
        System.out.println(JSONUtils.toJSONString(sumTargetN3(ints4, 34)));
    }

    // 1. 多重遍历，复杂度n方
    public static int[] sumTargetN1(int[] nums, int target) {
        // 遍历一遍是否存在和为目标值的对象
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return new int[2];
    }

    // 2. 方法1复杂度太高，这里可以构建字典，利用map特性遍历一遍就找到所需对象，复杂度n
    public static int[] sumTargetN2(int[] nums, int target) {
        // 将数组转化为Map字典，key为值，value为下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        // 遍历一遍获取对应值的下标
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                // 存在可以组成目标值的对象时，返回结果
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return null;
    }

    // 3. 方法2复杂度虽然不高，但是耗时长，存在2次循环，这里利用map赋值特性仅进行一次循环解决问题
    public static int[] sumTargetN3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        // 这里首尾同时进行赋值，在遍历赋值的同时进行判断
        for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {
            if (map.containsKey(target - nums[i])) {
                return map.get(target - nums[i]) < i ? new int[]{map.get(target - nums[i]), i} : new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
            if (map.containsKey(target - nums[j])) {
                return map.get(target - nums[j]) < j ? new int[]{map.get(target - nums[j]), j} : new int[]{j, map.get(target - nums[j])};
            }
            map.put(nums[j], j);
        }
        return null;
    }
}
