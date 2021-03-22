package com.xgsama.algorithm.others;

import com.xgsama.algorithm.model.ListNode;

import java.util.Stack;

/**
 * Test
 *
 * @author xgSama
 * @date 2021/3/17 21:07
 */
public class Test {

    static ListNode res = new ListNode(0);

    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        node1.next = node2;
//        ListNode node3 = new ListNode(3);
//        node2.next = node3;
//
//        ReverseList(node1);
//
//        while (res != null) {
//            System.out.println(res);
//            res = res.next;
//        }


    }

    public static ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode node = new ListNode(0);
        ListNode tmp = node;

        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        while (!stack.isEmpty()) {
            tmp.next = stack.pop();
            tmp = tmp.next;
        }

        return node.next;
    }

}
