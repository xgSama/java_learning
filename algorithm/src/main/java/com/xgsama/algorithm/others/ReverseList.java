package com.xgsama.algorithm.others;

import com.xgsama.algorithm.model.ListNode;

import static com.xgsama.algorithm.util.ListUtil.*;

/**
 * ReverseList
 *
 * @author xgSama
 * @date 2021/3/18 13:43
 */
public class ReverseList {

    public static void main(String[] args) {

        ListNode head = create(1, 2, 3);

        System.out.println("origin = " + listToString(head));
        ListNode reverse = reverse(head);
        System.out.println("reverse = " + listToString(reverse));


    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode tmp = null, cur = null;

        while (head != null) {

            cur = head;
            head = head.next;

            tmp = dummy.next;
            dummy.next = cur;
            dummy.next.next = tmp;

        }

        return dummy.next;
    }
}
