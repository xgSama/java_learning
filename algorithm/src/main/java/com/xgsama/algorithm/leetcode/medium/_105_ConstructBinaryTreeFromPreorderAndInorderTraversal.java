package com.xgsama.algorithm.leetcode.medium;

import com.xgsama.algorithm.model.TreeNode;

/**
 * _105_ConstructBinaryTreeFromPreorderAndInorderTraversal
 *
 * @author : xgSama
 * @date : 2021/11/26 17:17:50
 */
public class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    // 105. 从前序与中序遍历序列构造二叉树
    // 中序的第一位是根节点，在前序中找到这个，分出左右子树，然后递归
    public static void main(String[] args) {
        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] in = new int[]{9, 3, 15, 20, 7};

        _105_ConstructBinaryTreeFromPreorderAndInorderTraversal trr =
                new _105_ConstructBinaryTreeFromPreorderAndInorderTraversal();
        trr.buildTree(pre, in);
    }

public TreeNode buildTree(int[] preorder, int[] inorder) {
    return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
}

public TreeNode build(int[] preorder, int preStart, int preEnd,
                      int[] inorder, int inStart, int inEnd) {

    if (preStart > preEnd) return null;

    // 构建了根节点
    int rooVal = preorder[preStart];
    TreeNode treeNode = new TreeNode(rooVal);


    // 怎么把剩下的部分拆成两部分？
    // 1. 前序遍历的第一个节点上是中间的根节点，那么中序遍历的结果根据这个值就可以切分出左右子树了，找到根节点在中序的下标
    int index = -1;
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == rooVal) {
            index = i;
        }
    }

    // 中序的序列：left | root | right ,知道root在哪就能算出左子树有多少个节点了
    int leftSize = index - inStart;

    // 中序：left | root | right
    // 前序：root | left | right
    //
    treeNode.left = build(preorder, preStart + 1, preStart + leftSize,
            inorder, inStart, index - 1);
    treeNode.right = build(preorder, preStart + leftSize + 1, preEnd,
            inorder, index + 1, inEnd);

    return treeNode;
}
}
