package com.xgsama.algorithm.dynamic;

/**
 * Dynamic
 *
 * @author xgSama
 * @date 2020/12/22 11:03
 */
public class Dynamic {

    public static void main(String[] args) {
        cost(15);
    }

    //  You can't specify target table 'test_1226' for update in FROM clause

    public static void cost(int n) {
        int cost = Integer.MAX_VALUE;
        int[] f = new int[n + 1];
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            if (i - 1 >= 0) cost = Math.min(cost, f[i - 1]) + 1;
            if (i - 5 >= 0) cost = Math.min(cost, f[i - 5]) + 1;
            if (i - 11 >= 0) cost = Math.min(cost, f[i - 11]) + 1;
            f[i] = cost;

            System.out.println("f" + i + "=" + f[i]);
        }
    }
}
