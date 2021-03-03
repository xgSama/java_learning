package com.xgsama.jvm.reference;

import java.lang.ref.SoftReference;
import java.util.Arrays;

/**
 * SoftReferenceDemo
 *
 * @author xgSama
 * @date 2021/1/23 15:12
 */
public class SoftReferenceDemo {
    // -XX:+PrintGCDetails -Xms24M -Xmx24M
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(m.get()); // [B@1b6d3586
        System.gc();

        try {
            Thread.sleep( 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get()); // null
    }
}
