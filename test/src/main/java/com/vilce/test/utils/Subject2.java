package com.vilce.test.utils;

import com.vilce.common.utils.JSONUtils;

import java.util.Objects;

/**
 * @Description: 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * @ProjectName: learn
 * @Package: com.vilce.test.utils
 * @Author: lcz
 * @Date: 2021/7/16 下午8:57
 * @Version: 1.0
 */
public class Subject2 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l12 = new ListNode(3, new ListNode(6, new ListNode(4)));
        ListNode l21 = new ListNode(0);
        ListNode l22 = new ListNode(0);
        ListNode l31 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9,
                new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode l32 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        ListNode l41 = new ListNode(2, new ListNode(4, new ListNode(9)));
        ListNode l42 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9))));
        System.out.println(JSONUtils.toJSONString(listSum(l11, l12)));
        System.out.println(JSONUtils.toJSONString(listSum(l21, l22)));
        System.out.println(JSONUtils.toJSONString(listSum(l31, l32)));
        System.out.println(JSONUtils.toJSONString(listSum(l41, l42)));
    }

    // 1. 直接顺序相加并进位
    public static ListNode listSum(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode add = result;
        int ten = 0;
        while (l1 != null || l2 != null) {
            // 末位相加
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + ten;
            // 获取个位数和十位数
            ten = sum / 10;
            add.next = new ListNode(sum % 10);
            add = add.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (ten > 0) {
            add.next = new ListNode(ten);
        }
        return result.next;
    }
}
