package com.xgsama.algorithm.leetcode.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * _1_TwoSum
 *
 * @author : xgSama
 * @date : 2022/1/20 17:55:32
 */
public class _1_TwoSum {

    public static void main(String[] args) {

    }

public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int left = target - nums[i];
        if (map.containsKey(left)) {
            return new int[]{i, map.get(left)};
        }
        map.put(nums[i], i);
    }

    throw new IllegalArgumentException("null");
}
}
