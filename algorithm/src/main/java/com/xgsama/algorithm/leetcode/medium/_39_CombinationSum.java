package com.xgsama.algorithm.leetcode.medium;

import java.util.*;

/**
 * _39_CombinationSum
 *
 * @author : xgSama
 * @date : 2022/2/22 20:34:55
 */
public class _39_CombinationSum {

    /*
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target，
     * 找出candidates 中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */

    public static void main(String[] args) {
        _39_CombinationSum resolve = new _39_CombinationSum();

        int[] nums = {2, 2, 3, 6, 7};

        Arrays.sort(nums);
        List<List<Integer>> res = resolve.combinationSum(nums, 7);

        for (List<Integer> list : res) {
            System.out.println(list);
        }


    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        int len = candidates.length;

        if (len == 0) {
            return result;
        }

        Deque<Integer> subResult = new ArrayDeque<>();

        backtrack(candidates, target, 0, len, subResult, result);

        return result;
    }

    // 回溯

    /**
     * @param candidates 数组
     * @param target     和
     * @param begin      回溯递归开始位置
     * @param len        数组长度
     * @param sub        子结果
     * @param result     结果
     */
    public void backtrack(int[] candidates, int target, int begin, int len, Deque<Integer> sub, List<List<Integer>> result) {

        if (target == 0) {
            // 递归终止条件，已经凑够了target
            result.add(new ArrayList<>(sub));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            if (target < candidates[i]) {
                // candidates是正序数组，如果当前位置大于target就可以直接退出了
                return;
            }
            // 开始
            sub.addLast(candidates[i]);
            // 递归 因为可以复用，所以同一个回溯逻辑内开始都是一样的
            backtrack(candidates, target - candidates[i], i, len, sub, result);
            // 回撤
            sub.removeLast();
        }
    }

}
