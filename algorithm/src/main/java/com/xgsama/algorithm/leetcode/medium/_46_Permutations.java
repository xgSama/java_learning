package com.xgsama.algorithm.leetcode.medium;

import java.util.*;

/**
 * _46_Permutations
 *
 * @author : xgSama
 * @date : 2022/2/23 20:20:14
 */
public class _46_Permutations {

    /*
     *给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     */

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        _46_Permutations resolve = new _46_Permutations();
        for (List<Integer> integers : resolve.permute(nums)) {
            System.out.println(integers);
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        int len = nums.length;

        if (len == 0) {
            return res;
        }
        // 记录当前位置是否被使用过，方便判断
        boolean[] used = new boolean[len];
        Deque<Integer> sub = new ArrayDeque<>();

        backtrack(nums, 0, len, used, sub, res);
        return res;
    }

    /**
     * @param nums    数组
     * @param current 当前加入的是第几个数
     * @param len     数组长度
     * @param used    记录状态
     * @param sub     子结果
     * @param res     结果
     */
    public void backtrack(int[] nums, int current, int len, boolean[] used, Deque<Integer> sub, List<List<Integer>> res) {
        if (current == len) {
            res.add(new ArrayList<>(sub));
            return;
        }

        for (int i = 0; i < len; i++) {
            // 判断当前数有没有用过，没有用过的话可以当做第current个数加入子集
            if (!used[i]) {
                // 加入到子集中
                sub.addLast(nums[i]);
                used[i] = true;

                // 进入下一层，加入下一个数
                backtrack(nums, current + 1, len, used, sub, res);

                // 回撤
                used[i] = false;
                sub.removeLast();
            }
        }

    }
}
