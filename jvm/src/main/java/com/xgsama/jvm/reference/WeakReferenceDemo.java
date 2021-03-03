package com.xgsama.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * WeakReferenceDemo
 *
 * @author xgSama
 * @date 2021/1/23 15:50
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        WeakReference<RefTest> wr = new WeakReference<>(new RefTest());

        System.out.println(wr.get());
        System.gc();
        System.out.println(wr.get());
    }
}
