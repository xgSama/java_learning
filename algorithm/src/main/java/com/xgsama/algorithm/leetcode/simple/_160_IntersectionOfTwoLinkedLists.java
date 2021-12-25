package com.xgsama.algorithm.leetcode.simple;

import com.xgsama.algorithm.model.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * _160_IntersectionOfTwoLinkedLists
 *
 * @author : xgSama
 * @date : 2021/11/24 21:04:30
 */
public class _160_IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode tmp = headA;

        while (tmp != null) {
            set.add(tmp);
            tmp = tmp.next;
        }

        tmp = headB;
        while (tmp != null) {
            if (set.contains(tmp)) {
                return tmp;
            }
            tmp = tmp.next;
        }

        return null;
    }
}
