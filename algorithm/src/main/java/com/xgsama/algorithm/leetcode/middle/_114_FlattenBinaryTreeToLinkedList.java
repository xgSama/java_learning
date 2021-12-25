package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.model.TreeNode;

/**
 * _114_FlattenBinaryTreeToLinkedList
 *
 * @author : xgSama
 * @date : 2021/11/26 14:52:11
 */
public class _114_FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        // 递归终止条件
        if (root == null) {
            return;
        }
        // 分别拉平左右子树
        flatten(root.left);
        flatten(root.right);

        // 把以当前为根节点的二叉树展开成链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 把左树放到右树的位置
        root.left = null;
        root.right = left;

        // 找到新右树的尾节点，在把原先的右树接在这里
        TreeNode tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        tmp.right = right;
    }
}
