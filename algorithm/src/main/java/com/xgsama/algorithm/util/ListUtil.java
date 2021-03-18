package com.xgsama.algorithm.util;

import com.xgsama.algorithm.model.ListNode;

/**
 * ListUtil
 *
 * @author xgSama
 * @date 2021/3/18 14:11
 */
public class ListUtil {

    public static ListNode create(int... args) {
        ListNode dummy = new ListNode(-1);
        ListNode tmp = dummy;
        for (int arg : args) {
            tmp.next = new ListNode(arg);
            tmp = tmp.next;
        }

        return dummy.next;
    }

    public static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" -> ");
            head = head.next;
        }
        int last = sb.lastIndexOf(" -> ");
        if (last != -1) {
            sb.delete(last, sb.length());
        }

        return sb.toString();
    }
}
