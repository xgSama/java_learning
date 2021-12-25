package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.model.TreeNode;

/**
 * _98_ValidateBinarySearchTree
 *
 * @author : xgSama
 * @date : 2021/11/29 16:14:54
 */
public class _98_ValidateBinarySearchTree {


    public static void main(String[] args) {

        _98_ValidateBinarySearchTree v = new _98_ValidateBinarySearchTree();

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node2.left = node1;
        node2.right = node3;

        System.out.println(v.isValidBST(node2));

    }


    /*
        给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

        有效 二叉搜索树定义如下：
            1. 节点的左子树只包含 小于 当前节点的数。
            2. 节点的右子树只包含 大于 当前节点的数。
            3. 所有左子树和右子树自身必须也是二叉搜索树
     */

    public boolean isValidBST(TreeNode root) {


        return isValidBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    /**
     * 只限制当前节点与左右子节点的关系是不行的，我们要想办法吧root的约束传递下去
     *
     * @param root 当前节点
     * @param min  当前节点的最小值
     * @param max  当前节点的最大值
     * @return
     */
    public boolean isValidBST2(TreeNode root, int min, int max) {

        if (root == null) return true;

        if (root.val <= min || root.val >= max) {
            return false;
        }

        // 限定左⼦树的最⼤值是 root.val，右⼦树的最⼩值是 root.val
        return isValidBST2(root.left, min, root.val)
                && isValidBST2(root.right, root.val, max);
    }


    long pre = Long.MIN_VALUE;

    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 访问左子树
        if (!isValidBST3(root.left)) {
            return false;
        }

        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;

        // 访问右子树
        return isValidBST3(root.right);
    }


    boolean isValidBST4(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && root.val <= root.left.val)
            return false;
        if (root.right != null && root.val >= root.right.val)
            return false;
        return isValidBST4(root.left)
                && isValidBST4(root.right);
    }
}
