package com.xgsama.algorithm.linkedlist;

import com.xgsama.algorithm.model.ListNode;

import static com.xgsama.algorithm.util.ListUtil.*;

/**
 * Cycle
 *
 * @author xgSama
 * @date 2021/2/26 14:32
 */
public class Cycle {
    public static void main(String[] args) {

        ListNode head = create(1, 2, 3, 4, 5, 6);

        head.next.next.next.next.next.next = head.next;

        System.out.println(isCycle(head));

        System.out.println(getCycleEntryNode(head));

        System.out.println(getCycleLength(head));

    }

    public static boolean isCycle(ListNode header) {
        if (header == null) {
            return false;
        }

        ListNode fast = header;
        ListNode slow = header;

        // 快指针不为空，慢指针必不为空
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) return true;

        }

        return false;
    }

    public static ListNode getCycleEntryNode(ListNode header) {
        if (header == null) {
            return null;
        }

        ListNode fast = header;
        ListNode slow = header;

        // 快指针不为空，慢指针必不为空
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 指针第一次相遇点
            if (slow == fast) {

                // 将其中一个指针回到起点
                slow = header;
                // 指针每次向后移动一步，再次相遇点就是环的入口点
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }

    public static int getCycleLength(ListNode header) {
        if (header == null) {
            return 0;
        }

        ListNode fast = header;
        ListNode slow = header;
        int res = 0;

        // 快指针不为空，慢指针必不为空
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // fast的速度是slow的2倍，再次相遇比slow多走了一圈
            if (fast == slow) {
                do {
                    slow = slow.next;
                    fast = fast.next.next;
                    // fast比slow多走一步
                    res++;
                } while (slow != fast);

                return res;
            }
        }

        return res;
    }
}
