package com.xgsama.algorithm.leetcode.simple;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * @author xgSama
 * @date 2021/4/27 15:54
 */
public class _121_BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};

        System.out.println(maxProfit(nums));

    }

    /*
     * 每一天有两种状态，不持股和持股，当天的状态仅跟上一天有关
     * 若不持股：则跟前一天不持股状态一致或者卖掉前一天的持股
     * 若持股：  则跟前一天持股状态一致或者今天买入
     */
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        // 特殊判断
        if (len < 2) {
            return 0;
        }
        int[][] dp = new int[len][2];

        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数

        // 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第 2 天开始遍历
        for (int i = 1; i < len; i++) {
            // 不持股，跟前一天保持一致或者前一天持股今天卖掉
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 持股，跟前一天一致，或者今天买入
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        for (int[] ints : dp) {
            System.out.println(ints[0] + " | " + ints[1]);
        }
        return dp[len - 1][0];

    }


    public int maxProfit1(int[] prices) {
        int dp0 = 0;                    // 一直不买
        int dp1 = -prices[0];           // 只买了一次
        int dp2 = Integer.MIN_VALUE;    // 买了一次，卖了一次

        for (int i = 1; i < prices.length; i++) {
            // 前天买了股票，到现在还没卖  |   前天没买，今天买了	（什么时候买最划算）
            dp1 = Math.max(dp1, dp0 - prices[i]);
            // 前天就卖了股票  |   前天没卖，今天卖了		（什么时候卖最赚）
            dp2 = Math.max(dp2, dp1 + prices[i]);
        }
        return Math.max(dp0, dp2);
    }

    public int maxProfit2(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

}
