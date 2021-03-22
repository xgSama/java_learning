package com.xgsama.java.basis.dp;

import java.util.HashMap;

/**
 * ClimbStairs
 *
 * @author xgSama
 * @date 2020/12/9 11:53
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(15 & "a".hashCode());
        System.out.println("a".hashCode());

    }

    public static int climb(int n) {
        int[] f = new int[n];
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1];
            if (i >= 2) f[i] = (f[i] + f[i - 2]);
            if (i >= 3) f[i] = (f[i] + f[i - 3]);
        }

        return f[n];
    }
}
