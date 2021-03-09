package com.xgsama.algorithm.leetcode.simple;

import com.xgsama.algorithm.leetcode.model.ListNode;

/**
 * _21_MergeTwoSortedLists
 *
 * @author xgSama
 * @date 2021/3/4 11:00
 */
public class _21_MergeTwoSortedLists {

    /*
     * 合并两个升序的链表成一个新的升序链表
     *
     */
    public static void main(String[] args) {

        ListNode h1 = new ListNode(1);
        ListNode n1 = new ListNode(2);
        h1.next = n1;
        ListNode n2 = new ListNode(4);
        n1.next = n2;
        ListNode n3 = new ListNode(8);
        n2.next = n3;
        ListNode n4 = new ListNode(10);
        n3.next = n4;

        ListNode h2 = new ListNode(3);
        ListNode n12 = new ListNode(4);
        h2.next = n12;
        ListNode n22 = new ListNode(4);
        n12.next = n22;
        ListNode n32 = new ListNode(5);
        n22.next = n32;
        ListNode n42 = new ListNode(9);
        n32.next = n42;


        ListNode listNode = mergeTwoLists(h1, h2);

        while (listNode != null) {
            System.out.print(listNode.val + "  ");
            listNode = listNode.next;
        }


    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode node = new ListNode();
        ListNode tmp = node;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tmp.next = l1;

                l1 = l1.next;
            } else {
                tmp.next = l2;

                l2 = l2.next;
            }
            tmp = tmp.next;
        }

        // 有一个未合并完的链表
        tmp.next = l1 == null ? l2 : l1;

        return node.next;
    }
}
