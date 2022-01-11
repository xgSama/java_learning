package com.xgsama.algorithm.leetcode.medium;

/**
 * _931_MinFallingPathSum
 *
 * @author : xgSama
 * @date : 2022/1/9 12:56:04
 */
public class _931_MinFallingPathSum {

    public static void main(String[] args) {
        // matrix = [[2,1,3],[6,5,4],[7,8,9]]

        int[][] nums = {
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        };
        int[][] nums1 = {
                {82, 81},
                {69, 33}
        };

        int[][] nums3 = {
                {2}
        };

        System.out.println(minFallingPathSum(nums1));
        System.out.println(minFallingPathSum(nums3));
    }

    public static int minFallingPathSum(int[][] matrix) {

        int size = matrix.length;

        int[][] dp = new int[size][size];

        System.arraycopy(matrix[0], 0, dp[0], 0, size);

        for (int i = 1; i < size; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + matrix[i][0];
            for (int j = 1; j < size - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1])) + matrix[i][j];
            }
            dp[i][size - 1] = Math.min(dp[i - 1][size - 1], dp[i - 1][size - 2]) + matrix[i][size - 1];
        }

        int res = dp[size - 1][0];
        for (int i = 1; i < size; i++) {
            res = Math.min(dp[size - 1][i], res);
        }

        return res;

    }

}
