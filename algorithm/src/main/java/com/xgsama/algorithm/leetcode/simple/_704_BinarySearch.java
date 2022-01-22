package com.xgsama.algorithm.leetcode.simple;

/**
 * _704_BinarySearch
 *
 * @author : xgSama
 * @date : 2022/1/19 11:59:43
 */
public class _704_BinarySearch {

    // nums 升序 二分查找

    public static void main(String[] args) {

        int[] nums = {-1, 0, 3, 5, 9, 12};

        System.out.println(search(nums, 13));
    }

    public static int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (target == nums[mid]) {
                return mid;
            }
            if (target < nums[mid]) {
                right = mid - 1;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            }

        }

        return -1;
    }
}
