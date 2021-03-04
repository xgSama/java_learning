package com.xgsama.algorithm.leetcode.simple;

import java.util.Stack;

/**
 * _20_ValidParentheses
 *
 * @author xgSama
 * @date 2021/3/3 16:02
 */
public class _20_ValidParentheses {
    public static void main(String[] args) {

        String s = "){";
        System.out.println(isValid(s));

    }

    public static boolean isValid(String s) {

        if (s == null || s.equals("")) return false;

        if (s.length() % 2 != 0) return false;

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                // stack.isEmpty()：处理字符串以右括号开始的情况
                return false;
            }
        }

        return stack.isEmpty();
    }
}
