package com.xgsama.algorithm.leetcode.simple;

import java.util.Arrays;

/**
 * _27_RemoveElement
 *
 * @author xgSama
 * @date 2021/3/4 11:35
 */
public class _27_RemoveElement {

    /*
     * 给你一个数组nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    public static void main(String[] args) {

        int[] nums = new int[]{1};
        System.out.println(removeElement(nums, 1));

        int[] nums1 = new int[]{4};
        System.out.println(removeElement2(nums, 1));
    }

    public static int removeElement(int[] nums, int val) {

        int l = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[l++] = nums[i];
            }
        }

        return l;
    }

    public static int removeElement2(int[] nums, int val) {

        int l = 0;
        int n = nums.length;

        while (l < n) {
            if (nums[l] == val) {
                nums[l] = nums[--n];

            } else {
                l++;
            }
        }

        return l;
    }


}
