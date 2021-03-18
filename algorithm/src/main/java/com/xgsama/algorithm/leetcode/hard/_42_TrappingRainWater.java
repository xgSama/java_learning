package com.xgsama.algorithm.leetcode.hard;

import java.util.Scanner;
import java.util.Stack;

/**
 * _42_TrappingRainWater
 *
 * @author xgSama
 * @date 2021/3/4 18:09
 */
public class _42_TrappingRainWater {

    /*
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
    public static void main(String[] args) {

//        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] nums = new int[]{3, 2, 1, 3};

        System.out.println(trap(nums));

    }

    public static int trap(int[] height) {

        Stack<Integer> stack = new Stack<>();

        int water = 0;
        // 特殊情况
        if (height.length < 3) {
            return 0;
        }
        for (int i = 0; i < height.length; i++) {
            // 单调递减栈，遇见大于栈顶值的则进行出栈逻辑
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 栈顶元素
                int popnum = stack.pop();
                // 相同元素的情况例1，1
                while (!stack.isEmpty() && height[popnum] == height[stack.peek()]) {
                    stack.pop();
                }
                // 计算该层的水的单位
                if (!stack.isEmpty()) {
                    int temp = height[stack.peek()];// 栈顶元素值
                    // 高
                    int hig = Math.min(temp, height[i]) - height[popnum];
                    // 宽
                    int wid = i - stack.peek() - 1;
                    water += hig * wid;
                }

            }

            // 这里入栈的是索引
            stack.push(i);
        }
        return water;

    }


}
