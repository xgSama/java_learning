package com.xgsama.algorithm.leetcode;

/**
 * _14_LongestCommonPrefix
 *
 * @author xgSama
 * @date 2021/3/3 15:29
 */
public class _14_LongestCommonPrefix {
    public static void main(String[] args) {
        String[] s = new String[]{"aaa", "a"};

        System.out.println(longestCommonPrefix(s));
    }

    public static String longestCommonPrefix(String[] strs) {
        // 空值直接返回
        if (strs == null || strs.length == 0) {
            return "";
        }

        int count = strs.length;
        int len = strs[0].length();

        for (int i = 0; i < len; i++) {
            char cur = strs[0].charAt(i);

            for (int j = 1; j < count; j++) {
                // 长度判断要放在前面，否则可能出现下标越界情况 {“aa”, "a"}
                if (i == strs[j].length()|| cur != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }
}
