package com.xgsama.algorithm.leetcode.medium;

/**
 * _12_IntegerToRoman
 *
 * @author xgSama
 * @date 2021/3/10 16:59
 */
public class _12_IntegerToRoman {

    // 计算出六种特殊的情况，并添加到符号池中，此时共有12种符号可用的，且在罗马数字中从左往右一定是是递减的
    private static final int[] values = {   1000, 900,  500,  400, 100, 90,   50,  40,   10,   9,    5,   4,    1};
    private static final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};


    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // 在转换过程中，应该使用尽可能大的符号，在符号池中从左往右取值
        for (int i = 0; i < values.length && num >= 0; i++) {
            // 减去当前符号后如果此符号依旧满足条件，则应该继续使用该符号
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println("intToRoman(140) = " + intToRoman(140));

    }

}
