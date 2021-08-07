package com.xgsama.algorithm.leetcode.simple;

import java.util.ArrayList;
import java.util.Arrays;

public class _66_PlusOne {

    public static void main(String[] args) {
        int[] nums = {1, 2, 9};
        int[] ints = plusOne(nums);
        for (int anInt : ints) {
            System.out.print(anInt);
        }
    }

    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            // 当前位+1过后余10不为0，没有进位了可以返回
            if (digits[i] != 0) {
                return digits;
            }
            // 当前位+1过后余10为0，有进位，前一位数也+1
        }
        // 走到这里就说明位数有变化，是999这种
        digits = new int[len + 1];
        // 数组初始化全是0，首位置1即可
        digits[0] = 1;
        return digits;
    }
}
