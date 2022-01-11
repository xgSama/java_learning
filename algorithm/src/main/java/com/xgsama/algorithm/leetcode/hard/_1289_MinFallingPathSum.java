package com.xgsama.algorithm.leetcode.hard;

/**
 * _1289_MinFallingPathSum
 *
 * @author : xgSama
 * @date : 2022/1/9 15:16:03
 */
public class _1289_MinFallingPathSum {

    /*
    给你一个整数方阵arr，定义「非零偏移下降路径」为：从arr 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
    请你返回非零偏移下降路径数字和的最小值。
    * */
    public static void main(String[] args) {

        int[][] nums = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] nums2 = {
                {-73, 61, 43, -48, -36},
                {3, 30, 27, 57, 10},
                {96, -76, 84, 59, -15},
                {5, -49, 76, 31, -7},
                {97, 91, 61, -46, 67}};

        System.out.println(minFallingPathSum(nums2));
    }


    public static int minFallingPathSum(int[][] grid) {

        int size = grid.length;

        int[][] dp = new int[size][size];

        System.arraycopy(grid[0], 0, dp[0], 0, size);

        for (int i = 1; i < size; i++) {


            for (int j = 0; j < size; j++) {

                int min = Integer.MAX_VALUE;
                for (int k = 0; k < size; k++) {
                    if (k == j) {
                        continue;
                    }
                    min = Math.min(min, dp[i - 1][k]);
                }
                dp[i][j] = min + grid[i][j];
            }


        }

        int res = dp[size - 1][0];
        for (int i = 1; i < size; i++) {
            res = Math.min(dp[size - 1][i], res);
        }

        return res;


    }
}
