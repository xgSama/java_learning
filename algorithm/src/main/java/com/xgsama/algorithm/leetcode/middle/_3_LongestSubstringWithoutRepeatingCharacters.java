package com.xgsama.algorithm.leetcode.middle;

import java.util.LinkedList;
import java.util.Queue;

/**
 * _3_LongestSubstringWithoutRepeatingCharacters
 *
 * @author xgSama
 * @date 2021/3/9 10:18
 */
public class _3_LongestSubstringWithoutRepeatingCharacters {

    /*
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
     */
    public static void main(String[] args) {

        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) return 0;

        int res = 0;
        Queue<Character> queue = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            while (queue.contains(c)) {
                queue.poll();
            }
            queue.add(c);
            res = Math.max(res, queue.size());
        }

        return res;
    }
}
