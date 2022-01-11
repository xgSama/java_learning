package com.xgsama.algorithm.dynamic;

/**
 * Bag
 *
 * @author : xgSama
 * @date : 2022/1/10 21:38:38
 */
public class _01Bag {
    // n 件物品，体积为 v(i) 价值为w(i)
    // 背包容量为c

    public static void main(String[] args) {
        int w = 10;
        int[] p = {5, 5, 3, 4, 3};
        int[] g = {500, 400, 200, 300, 350};

        System.out.println(getBestGoldMining(w, p, g));
    }

    /**
     * 获得金矿最优收益
     *
     * @param w 工人数量
     * @param p 金矿开采需要的工人数量
     * @param g 金矿储量
     * @return 最大收益
     */
    public static int getBestGoldMining(int w, int[] p, int[] g) {
        // 创建表格
        int[][] resultTable = new int[g.length + 1][w + 1];
        // 填充表格 i个金矿
        for (int i = 1; i <= g.length; i++) {
            // j个人
            for (int j = 1; j <= w; j++) {
                if (j < p[i - 1]) {
                    resultTable[i][j] = resultTable[i - 1][j];
                } else {

                    // 1、不挖当前金矿
                    int v1 = resultTable[i - 1][j];

                    // 2、挖当前金矿
                    int v2 = resultTable[i - 1][j - p[i - 1]] + g[i - 1];

                    // 3、比较两种情况的收益
                    resultTable[i][j] = Math.max(v1, v2);
                }
            }
        }

        //返回最后1个格子的值
        return resultTable[g.length][w];
    }
}
