package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.model.ListNode;

import java.util.Stack;

/**
 * _19_RemoveNthNodeFromEndOfList
 *
 * @author xgSama
 * @date 2021/3/10 10:35
 */
public class _19_RemoveNthNodeFromEndOfList {

    /*
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        l1.next = l2;
//        ListNode l3 = new ListNode(3);
//        l2.next = l3;
//        ListNode l4 = new ListNode(4);
//        l3.next = l4;

        ListNode node = removeNthFromEnd(l1, 2);

        while (node != null) {
            System.out.print(l1.val + " ");
            node = node.next;
        }
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(-1, head);

        ListNode fast = head;
        ListNode slow = dummy;

        // 循环结束后，fast指向第n个节点，slow指向-1节点，两指针间隔n-1个节点
        for (int i = 0; i < n; i++) {
            if (fast != null)
                fast = fast.next;
            else return dummy.next;
        }
        // 当fast处于尾部时，slow指向倒数n+1个节点
        // fast是倒数第一个节点，与倒数第n个节点之间间隔了n-2个节点，所以此时slow位于倒数第n个节点的前一个节点
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        // 可能删除的恰好是head节点，所以返回dummy.next最合适
        return dummy.next;

    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode tmp = dummy;
        Stack<ListNode> stack = new Stack<>();

        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        ListNode pop = stack.peek();
        pop.next = pop.next.next;

        return dummy.next;
    }
}
