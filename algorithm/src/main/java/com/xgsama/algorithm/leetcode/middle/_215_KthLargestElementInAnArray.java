package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.sort.QuickSort;

/**
 * _215_KthLargestElementInAnArray
 *
 * @author xgSama
 * @date 2021/4/6 16:13
 */
public class _215_KthLargestElementInAnArray {

    // 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 1, 6, 99, 4, 2424, 214};
        System.out.println(findKthLargest(nums, 2));

    }

    public static int findKthLargest(int[] nums, int k) {

        new QuickSort().quickSort(nums, 0, nums.length - 1);

        return nums[nums.length - k];
    }


}
