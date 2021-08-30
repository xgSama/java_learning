package com.xgsama.algorithm.leetcode.middle;

import com.xgsama.algorithm.sort.QuickSort;

import java.util.Random;

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

    // 借助快排的思想，在不断的分区过程中，基准前面都是比基准小的数，后面都是比基准大的数
    // 所以当基准位于倒数K位时，就是我们需要找到的数
    public static int findKthLargest2(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    /**
     * @param nums  原数组
     * @param left  分区左边界
     * @param right 分区右边界
     * @param index 第K个大的数的下标
     * @return 第K个大的数
     */
    public static int quickSelect(int[] nums, int left, int right, int index) {
        int pivot_index = randomPartition(nums, left, right);
        if (pivot_index == index) {
            return nums[pivot_index];
        } else {
            // 我们的最终目的是让pivot_index和index的位置重合，
            // pivot是一个分界线，只要让他慢慢逼近index即可，反正它前面都是比他小的，后面都是比他大的，具体顺序是不重要的
            return pivot_index < index ?
                    quickSelect(nums, pivot_index + 1, right, index) :
                    quickSelect(nums, left, pivot_index - 1, index);
        }
    }

    static Random random = new Random();

    // 随机选取基准
    public static int randomPartition(int[] nums, int left, int right) {
        int pivot = random.nextInt(right - left + 1) + left;
        swap(nums, pivot, right);
        return partition(nums, left, right);
    }

    // 以right为基准进行分区
    public static int partition(int[] nums, int left, int right) {
        int x = nums[right], i = left - 1;
        for (int j = left; j < right; ++j) {
            if (nums[j] <= x) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
