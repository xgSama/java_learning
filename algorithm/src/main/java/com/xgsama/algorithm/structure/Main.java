package com.xgsama.algorithm.structure;

import java.util.Random;

/**
 * Main
 *
 * @author xgSama
 * @date 2021/3/25 15:31
 */
public class Main {
    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();

        Random random = new Random();

        int[] nums = new int[]{1, 6, 9, 7, 4, 3, 7, 2, 10};

        for (int i = 0; i < nums.length; i++) {
            skipList.add(nums[i]);
            System.out.println("add: " + nums[i]);
            skipList.print();
            System.out.println();
        }

        skipList.remove(3);
        skipList.print();


    }
}
