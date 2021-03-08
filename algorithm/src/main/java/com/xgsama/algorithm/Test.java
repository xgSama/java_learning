package com.xgsama.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Test
 *
 * @author xgSama
 * @date 2020/12/17 10:03
 */
public class Test {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 3};

        System.out.println(trap(nums));
    }

    public static int trap(int[] height) {

        if (height == null || height.length < 3) return 0;

        int res = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < height.length; i++) {

            // 递减栈 若大于栈顶元素执行出栈逻辑
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                Integer pop = stack.pop();
                while (!stack.isEmpty() && height[stack.peek()] == height[pop]) {
                    stack.pop();
                }

                if (!stack.isEmpty()) {
                    int peek = height[stack.peek()];

                    // 高
                    int hei = Math.min(peek, height[i]) - height[pop];
                    // 宽
                    int wid = i - stack.peek() - 1;

                    res += hei * wid;
                }

            }

            stack.push(i);
        }

        return res;

    }

}
