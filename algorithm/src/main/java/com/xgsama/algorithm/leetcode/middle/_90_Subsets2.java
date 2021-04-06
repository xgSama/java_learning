package com.xgsama.algorithm.leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * _90_Subsets2
 *
 * @author xgSama
 * @date 2021/3/31 9:51
 */
public class _90_Subsets2 {
    /*
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     */

    static List<Integer> tmp = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        int[] ints = {1, 2, 2};
        List<List<Integer>> lists = subsetsWithDup(ints);

        for (List<Integer> list : lists) {
            System.out.println(list);
        }

    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return res;
    }

    public static void dfs(boolean choosePre, int cur, int[] nums) {

        // 递归终止条件
        if (cur == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        // 递归，从最后向前回溯
        dfs(false, cur + 1, nums);

        // 回溯终止条件：若发现没有选择上一个数，且当前数字与上一个数相同，可直接跳过当前生成的子集
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }

        // 回溯
        tmp.add(nums[cur]);
        dfs(true, cur + 1, nums);
        tmp.remove(tmp.size() - 1);
    }
}
