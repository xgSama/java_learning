package com.xgsama.algorithm.leetcode.medium;

/**
 * _64_MinimumPathSum
 *
 * @author : xgSama
 * @date : 2022/1/8 20:54:09
 */
public class _64_MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        System.out.println(minPathSum(grid));
        System.out.println(minPathSum2(grid));
    }

    public static int minPathSum2(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col];

        for (int i = 0; i < row; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < col; j++) {
                if (i == 0) {
                    dp[j] = dp[j - 1] + grid[i][j];
                }
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }

        return dp[col - 1];
    }

    public static int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];

        dp[0][0] = grid[0][0];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                } else if (i > 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (j > 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
            }
        }

        return dp[row - 1][col - 1];
    }
}
