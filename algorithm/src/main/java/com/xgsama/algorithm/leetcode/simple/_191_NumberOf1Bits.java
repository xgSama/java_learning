package com.xgsama.algorithm.leetcode.simple;

import java.util.Random;

/**
 * _191_NumberOf1Bits
 *
 * @author xgSama
 * @date 2021/3/22 15:59
 */
public class _191_NumberOf1Bits {

    /*
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
     */

    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt();

        System.out.println(i);

        System.out.println(hammingWeight1(i));
        System.out.println(hammingWeight2(i));
    }

    public static int hammingWeight1(int n) {
        int res = 0;

        for (int i = 0; i < 32; i++) {
            // int32位，通过1左移依次比较每一位是否为1
            if ((n & (1 << i)) != 0) {
                res++;
            }
        }

        return res;
    }


    // you need to treat n as an unsigned value
    public static int hammingWeight2(int n) {
        int ret = 0;
        while (n != 0) {
            // n&n-1 可以消除n二进制的最后一个1
            n &= n - 1;
            ret++;
        }
        return ret;
    }

}