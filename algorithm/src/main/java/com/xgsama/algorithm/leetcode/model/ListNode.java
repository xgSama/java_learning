package com.xgsama.algorithm.leetcode.model;

/**
 * ListNode
 *
 * @author xgSama
 * @date 2021/3/9 9:56
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
