package com.xgsama.algorithm.linkedlist;

/**
 * Node
 *
 * @author xgSama
 * @date 2021/2/26 14:32
 */
public class Node {
    Node next;
    int data;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{ Node: " + data + " }";
    }
}
