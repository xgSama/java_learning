package com.xgsama.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * _34_FindFirstAndLastPosition
 *
 * @author : xgSama
 * @date : 2022/2/22 20:00:44
 */
public class _34_FindFirstAndLastPosition {

    /*
    * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
    * 如果数组中不存在目标值 target，返回 [-1, -1]。
    * */

    public static void main(String[] args) {

        _34_FindFirstAndLastPosition resolve = new _34_FindFirstAndLastPosition();

        int[] nums = {5, 7, 7, 8, 8, 10};

        System.out.println(resolve.binarySearch(nums, 6));
        System.out.println(Arrays.toString(new _34_FindFirstAndLastPosition().searchRange(nums, 5)));
    }


    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return new int[]{-1, -1};
        }

        int i = binarySearch(nums, target);

        if (i != -1) {
            int tmp = i, left = i, right = i;

            while (--tmp >= 0 && nums[tmp] == target) {
                left--;
            }
            tmp = i;
            while (++tmp < nums.length && nums[tmp] == target) {
                right++;
            }

            return new int[]{left, right};
        }

        return new int[]{-1, -1};

    }

    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
