package com.xgsama.algorithm.leetcode.medium;

import java.util.Stack;

/**
 * _331_VerifyPreorderSerializationOfABinaryTree
 *
 * @author xgSama
 * @date 2021/3/12 10:30
 */
public class _331_VerifyPreorderSerializationOfABinaryTree {

    /*
     * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
     * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #
     * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
     * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
     * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如"1,,3" 。
     */
    public static void main(String[] args) {
//        String s = "9,3,4,#,#,1,#,#,2,#,6,#,#";
//        String s = "1,#,#,#,#";
//        String s = "#";
        String s = "9,#,92,#,#";
        System.out.println(isValidSerialization(s));
    }

    public static boolean isValidSerialization(String preorder) {

        if (preorder == null || preorder.equals("") || preorder.startsWith("#")) return false;

        String[] nodes = preorder.split(",");

        Stack<String> stack = new Stack<>();


        for (String node : nodes) {
            stack.push(node);

            if (node.equals("#")) {
                if (stack.get(stack.size() - 2).equals(node)) {
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.push(node);
                }
            }

        }

        return true;
    }
}
