package com.xgsama.algorithm.leetcode.hard;

import com.xgsama.algorithm.model.ListNode;
import com.xgsama.algorithm.util.ListUtil;

/**
 * 25. K 个一组翻转链表
 *
 * @author xgSama
 * @date 2021/4/25 10:42
 */
public class _25_ReverseNodesInKGroup {

    /*
     * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
     * k是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 进阶：
     *  你可以设计一个只使用常数额外空间的算法来解决此问题吗？
     *  你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */

    public static void main(String[] args) {

        ListNode head = ListUtil.create(1, 2, 3, 4, 5);
        System.out.println(ListUtil.listToString(head));

        ListNode listNode = reverseKGroup(head, 2);
        System.out.println(ListUtil.listToString(listNode));

    }

    public static ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead;

        while (head != null) {

            // 要翻转部分的尾部
            ListNode tail = pre;

            // 检查是否够K个
            for (int i = 0; i < k; i++) {
                // 剩下的不足k个了直接返回
                if ((tail = tail.next) == null) {
                    return dummyHead.next;
                }
            }
            // 此时tail位于每组的第k个节点，需要翻转的部分是 pre.next到tail节点
            // 取到尾部的下一个节点，用于接入原链
            ListNode next = tail.next;
            // 翻转
            ListNode[] reverse = reverse(head, tail);
            head = reverse[0];
            tail = reverse[1];

            // 接回原链
            pre.next = head;
            tail.next = next;

            // 准备下一次翻转
            pre = tail;
            head = tail.next;
        }

        return dummyHead.next;
    }


    public static ListNode[] reverse(ListNode head, ListNode tail) {
        // 头插 prev：新链表的头部
        ListNode prev = tail.next;
        // p：原链表的头部
        ListNode p = head;
        while (prev != tail) {
            // 保留当前的下一个节点
            ListNode nex = p.next;

            // 新链表头部插入，prev指针前移
            p.next = prev;
            prev = p;

            // 老链表的头指针后移
            p = nex;
        }

        return new ListNode[]{tail, head};
    }
}
