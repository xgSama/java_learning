package com.xgsama.algorithm.leetcode.middle;

/**
 * _11_ContainerWithMostWater
 *
 * @author xgSama
 * @date 2021/3/9 11:10
 */
public class _11_ContainerWithMostWater {

    /*
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。说明：你不能倾斜容器。
     */

    public static void main(String[] args) {

        int[] nums = new int[]{10, 14, 10, 4, 10, 2, 6, 1, 6, 12};
        System.out.println("maxArea(nums) = " + maxArea(nums));

    }

    public static int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int res = 0;

        /*
         * 利用双指针分别从两头开始向中间移动以计算容器的容量
         * 在指针移动的过程中，容器的底是必然减少的，所以这时我们尽量保证容器的高度不会减少
         * 木桶能装多少水取决于最短的木板，所以小的那个指针是容器有效高度的关键，而大的指针则是容器容量上限的关键
         * 移动较小的指针可以有效的改变容器的容量，而移动大的指针则可能会降低容器的上限导致错过了最大值
         * 综上我们在移动指针时选择去移动较小的指针
         */
        while (left < right) {
            int tmp = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, tmp);

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }
}
