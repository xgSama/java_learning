package com.xgsama.algorithm.sort;

/**
 * HeapSort
 *
 * @author : xgSama
 * @date : 2021/12/13 23:33:53
 */
public class HeapSort {

    public void initHeap(int[] arr) {

    }


    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
