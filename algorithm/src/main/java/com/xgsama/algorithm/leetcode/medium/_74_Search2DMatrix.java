package com.xgsama.algorithm.leetcode.medium;

/**
 * _74_Search2DMatrix
 *
 * @author xgSama
 * @date 2021/3/30 10:07
 */
public class _74_Search2DMatrix {
    /*
     * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     */

    public static void main(String[] args) {


    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null) return false;

        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = row - 1; i >= 0; i--) {
            if (target >= matrix[i][0]) {
                for (int j = 0; j < col; j++) {
                    if (target == matrix[i][j]) {
                        return true;
                    }
                }
            }

        }

        return false;
    }
}
