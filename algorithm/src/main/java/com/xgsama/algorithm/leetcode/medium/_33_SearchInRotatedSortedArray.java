package com.xgsama.algorithm.leetcode.medium;

/**
 * _33_SearchInRotatedSortedArray
 *
 * @author : xgSama
 * @date : 2022/1/19 14:14:56
 */
public class _33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(nums, 12));
    }

    public static int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {

            }

            if (nums[mid] > target) {

            }
        }

        return -1;
    }
}
