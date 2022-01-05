package com.xgsama.algorithm.leetcode.medium;

/**
 * _62_UniquePaths
 *
 * @author : xgSama
 * @date : 2021/12/28 19:10:21
 */
public class _62_UniquePaths {

    public static void main(String[] args) {
        System.out.println(new _62_UniquePaths().uniquePaths(2, 2));
    }

    /*
        一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     */

    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {

                // 计算边缘之外的地方，有两个方向可以到达
                if (row > 0 && col > 0) {
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                } else if (row > 0) {
                    // col = 0, 计算第一行，只能从左边走来
                    dp[row][col] = dp[row - 1][col];
                } else if (col > 0) {
                    // row = 0, 计算第一列，只能从上边走来
                    dp[row][col] = dp[row][col - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
