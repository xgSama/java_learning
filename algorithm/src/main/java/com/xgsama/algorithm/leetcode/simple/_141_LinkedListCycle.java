package com.xgsama.algorithm.leetcode.simple;

import com.xgsama.algorithm.model.ListNode;

/**
 * _141_LinkedListCycle
 *
 * @author : xgSama
 * @date : 2022/1/20 17:29:58
 */
public class _141_LinkedListCycle {


    public boolean hasCycle(ListNode head) {

        if (head == null) return false;

        ListNode fast = head, slow = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;

    }
}
