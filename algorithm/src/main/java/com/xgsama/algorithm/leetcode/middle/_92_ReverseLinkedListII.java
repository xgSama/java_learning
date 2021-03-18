package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.model.ListNode;

import static com.xgsama.algorithm.leetcode.simple._206_ReverseLinkedList.reverse;

/**
 * _92_ReverseLinkedListII
 *
 * @author xgSama
 * @date 2021/3/18 18:58
 */
public class _92_ReverseLinkedListII {

    /*
     * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
     */

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        
        if (head == null || left == right) return head;

        // left可能位于头结点，所以在头结点前再虚拟出一个节点，这个节点是肯定不会变的
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;

        // 获取left的前一个节点：pre
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 从 pre 再走 right - left + 1 步，获取right节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 截取要反转的子链表
        ListNode leftNode = pre.next;  // 这是子链的头，反转后就是尾
        ListNode curr = rightNode.next;// 这是第三段的头，最终这两个要接在一起

        // 切断子链和主链的链接
        pre.next = null;
        rightNode.next = null;

        // 反转子链：leetcode:206
        reverse(leftNode);

        // 接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;

    }
}
