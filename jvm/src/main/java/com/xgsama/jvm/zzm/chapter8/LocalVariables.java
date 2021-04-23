package com.xgsama.jvm.zzm.chapter8;

/**
 * chapter8
 *
 * @author xgSama
 * @date 2021/4/22 14:36
 */
public class LocalVariables {
    /* -verbose：gc */
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;

        int b;
        System.gc();
    }
}
