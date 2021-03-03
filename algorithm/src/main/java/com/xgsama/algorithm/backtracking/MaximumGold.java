package com.xgsama.algorithm.backtracking;

/**
 * MaximumGold
 *
 * @author xgSama
 * @date 2020/12/17 15:49
 */
public class MaximumGold {

    public static void main(String[] args) {
        int[][] grid = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};


        System.out.println(getMaximumGold(grid));


    }

    public static int getMaximumGold(int[][] grid) {

        int res = 0;

        if (grid == null || grid.length == 0) return 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //函数dfs是以坐标(i,j)为起点，查找最大路径值
                res = Math.max(res, dfs(grid, i, j));
            }
        }

        return res;
    }


    public static int dfs(int[][] grid, int row, int col) {


        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0)
            return 0;

        int temp = grid[row][col];
        grid[row][col] = 0;
        
        int right = dfs(grid, row, col + 1);
        int left = dfs(grid, row, col - 1);
        int down = dfs(grid, row + 1, col);
        int up = dfs(grid, row - 1, col);
        int max = Math.max(left, Math.max(right, Math.max(up, down)));

        grid[row][col] = temp;
        //返回最大路径值
        return grid[row][col] + max;
    }


}
