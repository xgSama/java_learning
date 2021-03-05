package com.xgsama.algorithm.leetcode.simple;

/**
 * _35_SearchInsertPosition
 *
 * @author xgSama
 * @date 2021/3/4 15:48
 */
public class _35_SearchInsertPosition {
    /*
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     */
    public static void main(String[] args) {

        int[] nums = new int[]{2, 4, 5};
        System.out.println(searchInsert(nums, 3));

    }

    // 二分查找 当起始位置重合时 start = mid = end
    // 符合条件则返回当前位置 若不符合 则进行最后一次循环判断target是大还是小确定在前还是在后
    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (target > nums[mid]) {
                start = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return start;
    }
}
