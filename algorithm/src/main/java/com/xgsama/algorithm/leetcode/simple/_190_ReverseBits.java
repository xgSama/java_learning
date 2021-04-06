package com.xgsama.algorithm.leetcode.simple;

/**
 * _190_ReverseBits
 *
 * @author xgSama
 * @date 2021/3/29 15:02
 */
public class _190_ReverseBits {

    // 颠倒给定的 32 位无符号整数的二进制位。

    public static void main(String[] args) {
        System.out.println(reverseBits(9));
    }


    public static int reverseBits(int n) {

        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }

    public static int reverseBits2(int n) {

        int res = 0;
        for (int i = 0; i < 32; i++) {

            // res 左移一位，给n的最后一位留出位置
            res <<= 1;
            // n 和 1 与，取出 n 的最后一位，放在 res 的最后一位
            res += n & 1;
            // n 右移一位，把已经挪到 res 中的最后一位释放掉
            n >>= 1;
        }
        return res;
    }
}
