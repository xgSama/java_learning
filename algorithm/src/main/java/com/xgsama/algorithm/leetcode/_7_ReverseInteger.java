package com.xgsama.algorithm.leetcode;

/**
 * _7_ReverseInteger
 *
 * @author xgSama
 * @date 2021/3/3 11:08
 */
public class _7_ReverseInteger {

    /*
     * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围[-2^31, 2^31-1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     */
    public static void main(String[] args) {

        System.out.println(reverse(123));
        System.out.println(reverse(-123));


    }

    public static int reverse(int x) {

        int res = 0;

        while (x != 0) {
            int i = x % 10;

            if (res == Integer.MAX_VALUE / 10 && i > 7 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }

            if (res == Integer.MIN_VALUE / 10 && i < -8 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }

            res = res * 10 + i;

            x = x / 10;

        }

        return res;
    }
}
