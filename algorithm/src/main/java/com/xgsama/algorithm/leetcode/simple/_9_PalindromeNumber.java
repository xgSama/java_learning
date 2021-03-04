package com.xgsama.algorithm.leetcode.simple;

/**
 * _9_PalindromeNumber
 *
 * @author xgSama
 * @date 2021/3/3 13:58
 */
public class _9_PalindromeNumber {
    /*
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     *
     * 反转后比较
     */
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));

    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        } else {

            int reverse = _7_ReverseInteger.reverse(x);
            return x == reverse;

        }
    }
}
