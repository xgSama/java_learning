package com.xgsama.algorithm.model;

/**
 * TreeNode
 *
 * @author xgSama
 * @date 2021/4/8 14:27
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}
