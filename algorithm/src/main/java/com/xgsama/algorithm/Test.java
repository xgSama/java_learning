package com.xgsama.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Test
 *
 * @author xgSama
 * @date 2020/12/17 10:03
 */
public class Test {

    public static void main(String[] args) {
        for (List<String> list : solveNQueens(4)) {
            for (String s : list) {
                System.out.println(s);
            }

            System.out.println();
        }

    }

    public static List<List<String>> solveNQueens(int n) {

        char[][] chess = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }

        List<List<String>> res = new ArrayList<>();
        solve(res, chess, 0);

        return res;


    }

    public static void solve(List<List<String>> res, char[][] chess, int row) {

        // 递归终止条件
        if (row == chess.length) {
            res.add(construct(chess));
        }

        for (int col = 0; col < chess.length; col++) {
            // 判断当前位置是否可以放置皇后
            if (valid(chess, row, col)) {
                chess[row][col] = 'Q';
                solve(res, chess, row + 1);

                chess[row][col] = '.';
            }
        }
    }

    public static boolean valid(char[][] chess, int row, int col) {

        // 当前位置上方有没有皇后
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }

        // 当前位置左上方有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        // 当前位置右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        return true;

    }

    //把数组转为list
    private static List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            path.add(new String(chess[i]));
        }
        return path;
    }


}
