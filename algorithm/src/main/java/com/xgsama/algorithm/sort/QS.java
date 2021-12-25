package com.xgsama.algorithm.sort;

/**
 * QS
 *
 * @author : xgSama
 * @date : 2021/11/24 21:25:05
 */
public class QS {
    // 快排 选定基准 分区 临界条件

    public static void main(String[] args) {

        int[] arr = new int[]{1, 4, 2, 5, 1, 3};
        quick(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }

    }

public static void quick(int[] arr, int left, int right) {
    if (left > right) return;

    int pivot_index = partition(arr, left, right);

    quick(arr, 0, pivot_index - 1);
    quick(arr, pivot_index + 1, right);

}

public static int partition(int[] arr, int left, int right) {
    // 选取最左侧的值作为基准
    int pivot = arr[left];
    int i = left + 1, j = right;

    while (i <= j) {
        // 从左开始找一个比基准大的数
        while (i <= right && arr[i] <= pivot) {
            i++;
        }
        // 从右开始找一个比基准小的数
        while (j >= left && arr[j] > pivot) {
            j--;
        }

        // 将这两个数交换位置
        if (i <= j) swap(arr, i, j);
    }

    // 现在要把开始选好的基准放到中间来。
    // 此时可将数组看成三个部分 pivot small large
    // 那么现在 j的位置是small部分的最后一位，i是large位置的第一位
    // 显然，我们应该将基准转移到j的位置，才能保证在他左边的都是比他小的，右边都是比他大的
    swap(arr, j, left);

    return j;


}

public static void swap(int[] arr, int left, int right) {
    int tmp = arr[left];
    arr[left] = arr[right];
    arr[right] = tmp;
}
}
