package com.xgsama.algorithm.leetcode.medium;

/**
 * _5_LongestPalindromicSubstring
 * 5. 最长回文子串
 *
 * @author xgSama
 * @date 2021/2/26 10:47
 */
public class _5_LongestPalindromicSubstring {

    // 中心扩散
    public static void main(String[] args) {

        String s = "地满红花红满地";
        String s1 = null;
        String s2 = "a";

        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome(s1));
        System.out.println(longestPalindrome(s2));

    }

    public static String longestPalindrome(String s) {

        if (s == null || s.length() < 1) return "";
        if (s.length() == 1) return s;

        int len = s.length();

        int start = 0, end = 0;
        for (int i = 0; i < len - 1; i++) {
            int len1 = diffusion(s, i, i, len);
            int len2 = diffusion(s, i, i + 1, len);

            int l = Math.max(len1, len2);
            if (l > end - start + 1) {
                start = i - (l - 1) / 2;
                end = i + l / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    public static int diffusion(String s, int left, int right, int len) {
        while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
