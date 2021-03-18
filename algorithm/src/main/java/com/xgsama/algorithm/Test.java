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

        Test t = new Test();
        int[] nums1 = new int[]{3, 2, 1, 3};
        t.quickSort(nums1, 0, nums.length - 1);
        for (int num : nums1) {
            System.out.print(num + " ");
        }


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

    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = par(arr, left, right);

        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);

    }

    public int par(int[] arr, int left, int right) {

        int pivot = arr[right];
        int tail = left - 1;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, ++tail, i);
            }

        }

        swap(arr, tail + 1, right);


        return tail + 1;


    }

    public void swap(int[] arr, int i, int k) {
        int tmp = arr[i];
        arr[i] = arr[k];
        arr[k] = tmp;
    }

}
