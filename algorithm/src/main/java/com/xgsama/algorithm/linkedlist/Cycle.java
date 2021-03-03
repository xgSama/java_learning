package com.xgsama.algorithm.linkedlist;

/**
 * Cycle
 *
 * @author xgSama
 * @date 2021/2/26 14:32
 */
public class Cycle {
    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node1;

        System.out.println(isCycle(node1));

        System.out.println(getCycleEntryNode(node1));

        System.out.println(getCycleLength(node1));

    }

    public static boolean isCycle(Node header) {
        if (header == null) {
            return false;
        }

        Node fast = header;
        Node slow = header;

        // 快指针不为空，慢指针必不为空
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) return true;

        }

        return false;
    }

    public static Node getCycleEntryNode(Node header) {
        if (header == null) {
            return null;
        }

        Node fast = header;
        Node slow = header;

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

    public static int getCycleLength(Node header) {
        if (header == null) {
            return 0;
        }

        Node fast = header;
        Node slow = header;
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
