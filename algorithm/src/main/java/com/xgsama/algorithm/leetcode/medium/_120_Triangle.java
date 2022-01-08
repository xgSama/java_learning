package com.xgsama.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * _120_Triangle
 *
 * @author : xgSama
 * @date : 2022/1/6 22:58:05
 */
public class _120_Triangle {


    public static void main(String[] args) {
        // [[2],[3,4],[6,5,7],[4,1,8,3]]
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        ArrayList<Integer> integers2 = new ArrayList<>();
        integers2.add(3);
        integers2.add(4);
        ArrayList<Integer> integers3 = new ArrayList<>();
        integers3.add(6);
        integers3.add(5);
        integers3.add(7);
        ArrayList<Integer> integers4 = new ArrayList<>();
        integers4.add(4);
        integers4.add(1);
        integers4.add(8);
        integers4.add(3);

        List<List<Integer>> list = new ArrayList<>();
        list.add(integers);
        list.add(integers2);
        list.add(integers3);
        list.add(integers4);

        System.out.println(new _120_Triangle().minimumTotal(list));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();

        int[][] dp = new int[size][size];


        dp[0][0] = triangle.get(0).get(0);


        for (int i = 1; i < size; i++) {

            // f(i,j) = f(i-1,0) + v(i,j)
            dp[i][0] = triangle.get(i).get(0) + dp[i - 1][0];

            for (int j = 1; j < i; j++) {
                // f(i,j) = min(f(i-1,j-1),f(j-1,j)) + v(i,j)
                int left = dp[i - 1][j - 1];
                int right = dp[i - 1][j];
                dp[i][j] = Math.min(right, left) + triangle.get(i).get(j);
            }
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);

        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            res = Math.min(res, dp[size - 1][i]);
        }

        return res;
    }

}
