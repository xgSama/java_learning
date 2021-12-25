package com.xgsama.algorithm.sort;

/**
 * MergeSort
 *
 * @author : xgSama
 * @date : 2021/12/13 22:15:07
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 5, 3, 7, 3};
        new MergeSort().MergeSortRecursion(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    void MergeSortRecursion(int[] arr, int left, int right) {
        // 当待排序的序列长度为1时，递归开始回溯，进行merge操作
        if (left == right) return;

        int mid = (left + right) / 2;
        MergeSortRecursion(arr, left, mid);
        MergeSortRecursion(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    // 合并两个已排好序的数组A[left...mid]和A[mid+1...right]
    void merge(int[] arr, int left, int mid, int right) {

        int len = right - left + 1;
        int[] tmp = new int[len];
        int index = 0;
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            tmp[index++] = arr[i] <= arr[j] ? arr[i++] : arr[j++]; // 带等号保证归并排序的稳定性
        }

        // 左边的没复制完
        while (i <= mid) {
            tmp[index++] = arr[i++];
        }

        // 右边的没复制完
        while (j <= right) {
            tmp[index++] = arr[j++];
        }

        for (int k = 0; k < len; k++) {
            arr[left++] = tmp[k];
        }


    }

}