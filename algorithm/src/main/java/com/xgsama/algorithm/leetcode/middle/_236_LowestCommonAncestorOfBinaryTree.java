package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.model.TreeNode;

/**
 * _236_LowestCommonAncestorOfBinaryTree
 *
 * @author xgSama
 * @date 2021/4/8 14:26
 */
public class _236_LowestCommonAncestorOfBinaryTree {
    /*
     * 236. 二叉树的最近公共祖先
     *
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
     * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode l1 = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        root.left = l1;
        root.right = r1;

        System.out.println(lowestCommonAncestor(root, l1, r1));
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        /*
         注意p,q必然存在树内, 且所有节点的值唯一!!!
         递归思想, 对以root为根的(子)树进行查找p和q, 如果root == null || p || q 直接返回root
         表示对于当前树的查找已经完毕, 否则对左右子树进行查找, 根据左右子树的返回值判断:
         1. 左右子树的返回值都不为null, 由于值唯一左右子树的返回值就是p和q, 此时root为LCA
         2. 如果左右子树返回值只有一个不为null, 说明只有p和q存在与左或右子树中, 最先找到的那个节点为LCA
         3. 左右子树返回值均为null, p和q均不在树中, 返回null
         */

        if (root == null || root == p || root == q)
            return root;
        TreeNode l_res = lowestCommonAncestor(root.left, p, q);
        TreeNode r_res = lowestCommonAncestor(root.right, p, q);
        // 如果在当前节点的左右子树中分别找到了给定了两个节点，则当前节点就是最近的公共祖先
        if (l_res != null && r_res != null)
            return root;
        // 在左右子树中搜索的结果如果不是均不为null，说明给定的两个节点中存在包含关系，
        // 即一个节点在另一个节点的子树中，这时最近的公共祖先就是不为null的那个节点
        return l_res == null ? r_res : l_res;
    }
}
