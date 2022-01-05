package com.xgsama.algorithm.leetcode.simple;

import com.xgsama.algorithm.model.TreeNode;

import java.util.*;

/**
 * _151_SymmetricTree
 *
 * @author : xgSama
 * @date : 2021/12/26 00:09:42
 */
public class _151_SymmetricTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;


        ArrayList<TreeNode> treeNodes = new ArrayList<>();

        treeNodes.add(node1.left);
        treeNodes.add(node2.right);
        System.out.println(treeNodes.size());

        treeNodes.remove(0);
        System.out.println(treeNodes.size());


    }

    // 根据镜像二叉树的定义，镜像的两个节点值是相等的，我们需要找一个办法把树上的节点安装这个顺序放到一个list里面
    // 那么这个list从头开始一定存在是两两一组相等的规律：22 33 44这种
    // 做法：边判断边加入，初始化链表先把根节点的左右节点放进去
    // 遍历这个链表，先拿出头部两个元素，判断相等后再按照镜像的规律把它俩的四个子节点放进去，然后进入下一此遍历
    public boolean isSymmetric2(TreeNode root) {

        if (root == null) {
            return true;
        }

        // 会频繁的增删，使用LinkedList
        LinkedList<TreeNode> list = new LinkedList<>();

        list.add(root.left);
        list.add(root.right);

        while (list.size() > 0) {

            TreeNode left = list.removeFirst();
            TreeNode right = list.removeFirst();
            // 都为空说明到底了，不用再往list里加了
            if (left == null && right == null) {
                continue;
            }
            // 两个节点不想等，直接返回false
            if ((left == null || right == null) || (left.val != right.val)) {
                return false;
            }

            // 到这left和right都不可能为空了，将他们该相等的节点按照顺序加入list
            list.add(left.left);
            list.add(right.right);

            list.add(left.right);
            list.add(right.left);
        }
        return true;
    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode left, TreeNode right) {
        // 说明已经到了叶子节点的下一次层了
        if (left == null && right == null) {
            return true;
        }
        // 有的到叶子节点的下一层了，有的还没到，不对称了
        if (left == null || right == null) {
            return false;
        }
        // 值不一样，也是不对称了
        if (left.val != right.val) {
            return false;
        }

        // 到这就说明了当前俩节点符合要求，接来就看他们的子节点了
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
