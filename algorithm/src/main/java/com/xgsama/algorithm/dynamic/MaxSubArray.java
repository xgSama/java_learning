package com.xgsama.algorithm.dynamic;

/**
 * MaxSubArray
 *
 * @author xgSama
 * @date 2020/12/22 11:39
 */
public class MaxSubArray {
    public static void main(String[] args) {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArray(nums));

    }

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        // 边界条件
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < len; i++) {
            // 转移公式
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
