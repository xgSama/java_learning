package com.xgsama.algorithm.leetcode.simple;

/**
 * _28_Implement_strStr
 *
 * @author xgSama
 * @date 2021/3/4 14:40
 */
public class _28_Implement_strStr {

    /*
     * 给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回 -1。
     */
    public static void main(String[] args) {

        String s1 = "";
        String s2 = "q";
        System.out.println(strStr(s1, s2));

    }

    public int strStr1(String haystack, String needle) {

        int s = (haystack += " ").length();
        int n = needle.length();

        if (s < n) return -1;

        for (int i = 0; i < s - n + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0) && haystack.substring(i, i + n).equals(needle)) return i;
        }

        return -1;

    }

    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.equals("")) {
            return 0;
        }


        int s = (haystack += " ").length();
        int n = needle.length();

        if (s < n) return -1;

        for (int i = 0; i < s - n + 1; i++) {
            if (haystack.charAt(i) != needle.charAt(0)) continue;
            if (haystack.substring(i, i + n).equals(needle)) return i;
        }

        return -1;

    }


}
