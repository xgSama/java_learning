package com.xgsama.algorithm.leetcode.middle;

import java.util.Arrays;

/**
 * _300_LongestIncreasingSubsequence
 *
 * @author xgSama
 * @date 2021/3/8 10:51
 */
public class _300_LongestIncreasingSubsequence {

    /*
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     */

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println(lengthOfLIS(nums));


    }


    public static int lengthOfLIS(int[] nums) {

        // dp[i]记录的是以nums[i]结尾的子数组的最大递增子序列长度
        int[] dp = new int[nums.length];
        int max = 0;
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // j可看成以下标j结尾的子序列
                // 依次判断nums[i]是否可以放在j后面且是否是当前最大递增子序列
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }
}
