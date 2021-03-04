package com.xgsama.algorithm.sort;

import java.time.Instant;
import java.util.Random;

/**
 * BubbleSort
 *
 * @author xgSama
 * @date 2021/2/24 16:54
 */
public class BubbleSort {

    public static void main(String[] args) {
        int num = 100000;

        Random random = new Random();

        int[] arr = new int[num];

        for (int i = 0; i < num; i++) {
            arr[i] = random.nextInt();
        }

        long l = Instant.now().toEpochMilli();
        sort(arr);
        System.out.println(Instant.now().toEpochMilli() - l);

//        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = i; j < arr.length -1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
