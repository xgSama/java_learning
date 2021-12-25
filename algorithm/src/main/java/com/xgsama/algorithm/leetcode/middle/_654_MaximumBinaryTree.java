package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.model.TreeNode;

/**
 * _654_MaximumBinaryTree
 *
 * @author : xgSama
 * @date : 2021/11/26 14:55:20
 */
public class _654_MaximumBinaryTree {
    /*
        给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
            1. 二叉树的根是数组 nums 中的最大元素。
            2. 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
            3. 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
         返回有给定数组 nums 构建的 最大二叉树 。
     */

    public static void main(String[] args) {
        _654_MaximumBinaryTree maximumBinaryTree = new _654_MaximumBinaryTree();
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};

        maximumBinaryTree.constructMaximumBinaryTree(nums);
    }


public TreeNode constructMaximumBinaryTree(int[] nums) {
    return createTree(nums, 0, nums.length - 1);
}

public TreeNode createTree(int[] nums, int left, int right) {
    int maxIndex = findMaxIndex(nums, left, right);

    if (maxIndex == -1) return null;

    TreeNode treeNode = new TreeNode(nums[maxIndex]);

    TreeNode leftNode = createTree(nums, left, maxIndex - 1);
    TreeNode rightNode = createTree(nums, maxIndex + 1, right);


    treeNode.left = leftNode;
    treeNode.right = rightNode;

    return treeNode;
}

public int findMaxIndex(int[] nums, int left, int right) {

    if (left > right) return -1;

    int index = left;

    for (int i = left + 1; i <= right; i++) {
        if (nums[i] > nums[index]) {
            index = i;
        }
    }

    return index;
}
}
