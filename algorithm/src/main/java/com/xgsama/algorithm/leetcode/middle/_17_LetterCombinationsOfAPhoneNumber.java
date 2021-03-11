package com.xgsama.algorithm.leetcode.middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * _17_LetterCombinationsOfAPhoneNumber
 *
 * @author xgSama
 * @date 2021/3/11 10:23
 */
public class _17_LetterCombinationsOfAPhoneNumber {

    /*
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */

    public static void main(String[] args) {
        List<String> list = letterCombinations("234");
        for (String s : list) {
            System.out.print(s + " ,");
        }
    }


    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();

        if (digits == null || digits.equals("")) return list;

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrack(list, map, digits, 0, new StringBuilder());

        return list;
    }

    public static void backtrack(List<String> res, Map<Character, String> map, String digits, int index, StringBuilder str) {

        // 边界条件
        if (index == digits.length()) {
            res.add(str.toString());
            return;
        }

        char c = digits.charAt(index);
        String chars = map.get(c);

        for (int i = 0; i < chars.length(); i++) {
            // 添加
            str.append(chars.charAt(i));
            // 回溯
            backtrack(res, map, digits, index + 1, str);

            // 撤销
            str.deleteCharAt(index);
        }
    }
}
