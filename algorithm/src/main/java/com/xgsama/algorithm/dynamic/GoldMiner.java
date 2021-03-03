package com.xgsama.algorithm.dynamic;

import java.util.Arrays;

/**
 * GoldMiner
 * 黄金矿工问题
 *
 * @author xgSama
 * @date 2021/3/1 11:16
 */
public class GoldMiner {

    public static void main(String[] args) {

        int w = 10;
        int[] p = new int[]{5, 5, 3, 4, 3};
        int[] g = new int[]{400, 500, 200, 300, 350};

//        System.out.println(getBestGoldMining(w, p, g));
        System.out.println(getBestGoldMiningV2(w, p, g));
        System.out.println(getBestGoldMining(w, p.length, p, g));

    }

    /*
     * 很久很久以前，有一位国王拥有5座金矿，每座金矿的黄金储量不同，
     * 需要参与挖掘的工人人数也不同。例如有的金矿储量是500kg黄金，需
     * 要5个工人来挖掘；有的金矿储量是200kg黄金，需要3个工人来挖掘……
     * 如果参与挖矿的工人的总数是10。每座金矿要么全挖，要么不挖，不能
     * 派出一半人挖取一半的金矿。要求用程序求出，要想得到尽可能多的黄
     * 金，应该选择挖取哪几座金矿？
     */

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
        // 填充表格
        for (int i = 1; i <= g.length; i++) {
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

        for (int[] ints : resultTable) {
            System.out.println(Arrays.toString(ints));
        }

        //返回最后1个格子的值
        return resultTable[g.length][w];
    }

    /**
     * @param w 工人数量
     * @param n 金矿数量
     * @param p 金矿开采需要的工人数量
     * @param g 金矿储量
     * @return 最优
     */
    public static int getBestGoldMining(int w, int n, int[] p, int[] g) {
        if (w == 0 || n == 0) {
            return 0;
        }
        if (w < p[n - 1]) {
            return getBestGoldMining(w, n - 1, p, g);
        }
        return Math.max(getBestGoldMining(w, n - 1, p, g),
                getBestGoldMining(w - p[n - 1], n - 1, p, g) + g[n - 1]);
    }

    public static int getBestGoldMiningV2(int w, int[] p, int[] g) {
        //创建当前结果
        int[] results = new int[w + 1];
        //填充一维数组
        for (int i = 1; i <= g.length; i++) {
            // 在计算下一行时，要从右向左统计
            // 原因：在计算v2值时，要获取比当前下标小的上一行的数据，
            // 如果从左往右统计，当前位置左侧的数据已经全是本行的新数据，获取不到上一行的老数据导致结果错误
            for (int j = w; j >= 1; j--) {
                if (j >= p[i - 1]) {
                    // 1、不挖当前行
                    int v1 = results[j];
                    // 2、挖当前行
                    int v2 = results[j - p[i - 1]] + g[i - 1];
                    // 3、比较两种情况的收益
                    results[j] = Math.max(results[j], results[j - p[i - 1]] + g[i - 1]);
                }
            }

            System.out.println(Arrays.toString(results));
        }
        //返回最后1个格子的值
        return results[w];
    }
}

/*
[0, 0, 0, 0, 0, 400, 400, 400, 400, 400, 800]
[0, 0, 0, 0, 0, 500, 500, 500, 500, 500, 1000]
[0, 0, 0, 200, 200, 500, 500, 500, 700, 700, 1000]
[0, 0, 0, 200, 300, 500, 500, 500, 700, 800, 1000]
[0, 0, 0, 350, 350, 500, 700, 700, 850, 1050, 1050]
 */