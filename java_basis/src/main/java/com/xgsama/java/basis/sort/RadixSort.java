package com.xgsama.java.basis.sort;

import java.util.Arrays;

/**
 * RadixSort
 *
 * @author xgSama
 * @date 2020/11/26 17:18
 */
public class RadixSort {

    /**
     * 基数排序
     * <p>
     * 分类 ------------- 内部非比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(n * dn)
     * 最优时间复杂度 ---- O(n * dn)
     * 平均时间复杂度 ---- O(n * dn)
     * 所需辅助空间 ------ O(n * dn)
     * 稳定性 ----------- 稳定
     */
    public static void radixSort(int[] arr) {

        // 将数组中最大的数字max找到
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("max=" + max);
        // 十个桶
        int[][] buckets = new int[10][arr.length];
        // 该数组存放每一个桶中元素的数量,这个数组的索引和桶的索引对应
        int[] bucketCount = new int[10];

        // 需要排序的次数肯定就是max的位数
        int sortNum = Integer.toString(max).length();
        for (int j = 0; j < sortNum; j++) {
            // 遍历数组，第一次排序是用个位进行比较,第二次是用十位进行比较
            for (int i : arr) {
                // 取出数组中每一个数字的某一位上的数字,这个数字就是桶的索引
                int val = (int) (i / Math.pow(10, j) % 10);
                // 如果val == 2,那就放在第二个桶中，第二桶中也可能有bucketCount[val]个元素了，所以放完元素之后需要++
                buckets[val][bucketCount[val]++] = i;
            }

            printTwo(buckets);

            // 前面已经将元素都放进桶里了，现在取出来放到数组中
            int index = 0;
            for (int n = 0; n < bucketCount.length; n++) {
                //遍历bucketCount中的元素，如果不为0，说明该对应的桶中有元素
                if (bucketCount[n] != 0) {
                    for (int x = 0; x < bucketCount[n]; x++) {
                        //获取桶中的元素，然后放入数组中，并将桶中该元素置为0
                        arr[index++] = buckets[n][x];
                        buckets[n][x] = 0;
                    }
                }
                //每一次将桶里的元素取出来的时候，需要将对应数组中表示该桶中元素数量置为0
                //如果不置为0，那么在第二次从桶中拿元素放到数组中的时候，遍历bucketCount中的数据是之前桶中元素的数据（也许有6个桶中有数据）
                bucketCount[n] = 0;
            }
            System.out.println("第" + (j + 1) + "次排序的结果为：" + Arrays.toString(arr));

        }

    }

    /**
     * 遍历二维数据中的元素
     */
    public static void printTwo(int[][] arrs) {
        for (int[] arr : arrs) {
            for (int i : arr) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        int[] arr = {4, 13, 5, 112, 46};
        int[] arr = {329, 457, 657, 839, 436, 720, 355};
        radixSort(arr);

    }

}