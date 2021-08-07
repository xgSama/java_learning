package com.xgsama.algorithm.leetcode.simple;

public class _53_MaximumSubarray {
    /*
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     */

    public static void main(String[] args) {
        int[] ints = {1, 2, -4, 3, 6};
        System.out.println(maxSubArray(ints));
        System.out.println(maxSubArray2(ints));
    }

    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.print("nums:\t");
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();

        System.out.print("d  p:\t");
        for (int i : dp) {
            System.out.print(i + "\t");
        }
        System.out.println();

        return max;
    }

    public static int maxSubArray2(int[] nums) {
        int max = nums[0];
        int length = nums.length;
        int prev = nums[0];

        for (int i = 1; i < length; i++) {
            prev = Math.max(prev + nums[i], nums[i]);
            max = Math.max(max, prev);
        }

        return max;
    }
}
