package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.leetcode.model.ListNode;

import java.util.Stack;

/**
 * _2_AddTwoNumbers
 *
 * @author xgSama
 * @date 2021/3/9 9:54
 */
public class _2_AddTwoNumbers {

    /*
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     */
    public static void main(String[] args) {
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进位
        int carry = 0;
        ListNode res = new ListNode(-1);
        ListNode head = res;

        // 当l1、l2为空也没有进位的时候，说明已经加完了，否则执行加法
        while (l1 != null || l2 != null || carry != 0) {
            int tmp = carry;
            // l1、l2若不为空就把他们的值加上，并移动指针到下一位
            if (l1 != null) {
                tmp += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                tmp += l2.val;
                l2 = l2.next;
            }
            // 获取进位
            carry = tmp / 10;
            res.next = new ListNode(tmp % 10);
            res = res.next;
        }

        return head.next;
    }
}
