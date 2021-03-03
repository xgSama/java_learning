package com.xgsama.algorithm.sort;

import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

/**
 * QuickSort
 *
 * @author xgSama
 * @date 2021/2/24 14:54
 */
public class QuickSort {

    public static void main(String[] args) {

        QuickSort q = new QuickSort();
        Random random = new Random();
        int size = 100000000;
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
//        int[] arr = new int[]{4, 3};
//        System.out.println(Arrays.toString(arr));
        long l = Instant.now().toEpochMilli();
        System.out.println(l);
        q.quickSort(arr, 0, arr.length - 1);
        System.out.println(Instant.now().toEpochMilli() - l);
//        System.out.println(Arrays.toString(arr));


    }

    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot_index = partition(arr, left, right);

        quickSort(arr, left, pivot_index - 1);
        quickSort(arr, pivot_index + 1, right);
    }

    public void quickSort2(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot_index = partition2(arr, left, right);

        quickSort2(arr, left, pivot_index - 1);
        quickSort2(arr, pivot_index + 1, right);
    }

    // 划分函数 单边
    private int partition(int[] arr, int left, int right) {

        // 选取基准
        int pivot = arr[right];

        // tail为小于基准的子数组的最后一个元素的下标
        int tail = left - 1;

        // 把小于pivot的元素放到前一个子数组
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, ++tail, i);
            }
        }

        // 把pivot放到前一个子数组末尾，剩下的都是大于基准的子数组
        // 该操作可能把后面元素的稳定性打乱，所以快排是不稳定的算法
        swap(arr, tail + 1, right);

        return tail + 1;
    }

    // 双边快排
    private int partition2(int[] arr, int start, int end) {
        int pivot = arr[start];
        int left = start;
        int right = end;

        while (left != right) {
            // //控制right 指针比较并左移
            while (left < right && arr[right] > pivot) {
                right--;
            }

            while (left < right && arr[left] <= pivot) {
                left++;
            }

            if (left < right) {
                swap(arr, left, right);
            }
        }

        // pivot 和指针重合点交换
        arr[start] = arr[left];
        arr[left] = pivot;

        return left;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
