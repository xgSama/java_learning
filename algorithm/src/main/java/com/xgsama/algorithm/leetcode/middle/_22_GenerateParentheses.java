package com.xgsama.algorithm.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * _22_GenerateParentheses
 *
 * @author xgSama
 * @date 2021/3/11 13:59
 */
public class _22_GenerateParentheses {
    /*
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     */

    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        backtrack(res, 0, 0, n, new StringBuilder());
        return res;

    }

    /**
     * 回溯算法
     * 因为在一对括号中左括号是必然在前面的，所以在能添加左括号的条件下优先添加左括号，然后添加右括号去闭合它
     * 当左括号数量到达上限后，然后将已添加的左括号从后向前依次替换成右括号进行尝试
     * 然后尝试将左括号依次换成右括号再执行以上逻辑
     *
     * @param res   最终结果
     * @param open  已有的左括号数
     * @param close 已有的右括号数
     * @param pairs 需要生成的括号对数
     * @param sb    当前次回溯生成的字符串
     */
    public static void backtrack(List<String> res, int open, int close, int pairs, StringBuilder sb) {
        // 边界条件，符号添加规则决定了到达边界的括号都是有效的
        if (sb.length() >= pairs * 2) {
            res.add(sb.toString());
            return;
        }

        // 优先添加左括号
        if (open < pairs) {
            // 添加
            sb.append("(");
            // 尝试添加下一个
            backtrack(res, open + 1, close, pairs, sb);
            // 撤销本次操作
            sb.deleteCharAt(sb.length() - 1);
        }

        // 当左括号数量已达上限，或者从上一步撤销操作过后尝试添加右括号
        // close<open说明有括号还没有闭合，添加一个右括号去闭合他
        if (close < open) {
            sb.append(")");
            backtrack(res, open, close + 1, pairs, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
