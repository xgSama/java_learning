package com.xgsama.algorithm.backtracking;

/**
 * BurstBalloons
 *
 * @author xgSama
 * @date 2020/12/24 10:47
 */
public class BurstBalloons {

    static int res = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int[] balloons = {3, 1, 5, 8};
        BurstBalloons b = new BurstBalloons();
        System.out.println(b.maxCoins(balloons));
    }

    public int maxCoins(int[] nums) {

        int res = Integer.MIN_VALUE;

        backtrack(nums, 0, res);

        return res;
    }

    public void backtrack(int[] nums, int score, int res) {
        //递归终止条件
        if (isEmpty(nums)) {
            res = Math.max(res, score);
            System.out.println(res);
            return;
        }

        // 回溯
        for (int i = 0; i < nums.length; i++) {
            // 跳过已戳的
            if (nums[i] == -1) continue;
            int prev = i - 1;
            while (prev >= 0 && nums[prev] == -1) prev--;

            int next = i + 1;
            while (next < nums.length && nums[next] == -1) next++;


            int cur = (prev < 0 ? 1 : nums[prev]) * nums[i] * (next >= nums.length ? 1 : nums[next]);


            int temp = nums[i];
            // 戳
            nums[i] = -1;

            // 递归
            backtrack(nums, cur + score, res);

            // 撤回操作
            nums[i] = temp;
        }

    }

    public boolean isEmpty(int[] nums) {
        for (int num : nums) {
            if (num != -1) return false;
        }

        return true;
    }

}
