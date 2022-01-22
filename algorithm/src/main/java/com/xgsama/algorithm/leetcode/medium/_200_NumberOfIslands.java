package com.xgsama.algorithm.leetcode.medium;

/**
 * _200_NumberOfIslands
 *
 * @author : xgSama
 * @date : 2022/1/19 17:44:22
 */
public class _200_NumberOfIslands {

    /*

    给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围

    输入：grid = [
                  ["1","1","1","1","0"],
                  ["1","1","0","1","0"],
                  ["1","1","0","0","0"],
                  ["0","0","0","0","0"]
                ]
     输出：1
     */

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'},
        };

        System.out.println(numIslands(grid));
    }

public static int numIslands(char[][] grid) {

    int length = grid[0].length;
    int width = grid.length;
    int res = 0;

    for (int i = 0; i < width; i++) {
        for (int j = 0; j < length; j++) {
            if (grid[i][j] == '1') {
                res++;
                dfs(grid, i, j, length, width);
            }
        }
    }

    return res;
}

public static void dfs(char[][] grid, int row, int col, int length, int width) {
    if (row < 0 || col < 0 || row >= width || col >= length || grid[row][col] == '0') {
        return;
    }

    grid[row][col] = '0';
    dfs(grid, row + 1, col, length, width);
    dfs(grid, row - 1, col, length, width);
    dfs(grid, row, col + 1, length, width);
    dfs(grid, row, col - 1, length, width);
}
}
