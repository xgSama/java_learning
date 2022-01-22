package com.xgsama.algorithm.leetcode.medium;

import com.xgsama.algorithm.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * _102_BinaryTreeLevelOrderTraversal
 *
 * @author : xgSama
 * @date : 2022/1/20 17:00:41
 */
public class _102_BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {

    }


public static List<List<Integer>> levelOrder(TreeNode root) {

    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;


    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {

        int currentLevelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        for (int i = 0; i < currentLevelSize; i++) {
            TreeNode poll = queue.poll();
            currentLevel.add(poll.val);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        res.add(currentLevel);
    }


    return res;
}
}
