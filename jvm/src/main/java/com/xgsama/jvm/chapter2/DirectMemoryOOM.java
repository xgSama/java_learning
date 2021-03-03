package com.xgsama.jvm.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * DirectMemoryOOM
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author xgSama
 * @date 2020/12/12 15:24
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}

/*
 * Exception in thread "main" java.lang.OutOfMemoryError
 * 	at sun.misc.Unsafe.allocateMemory(Native Method)
 * 	at com.xgsama.jvm.chapter2.DirectMemoryOOM.main(DirectMemoryOOM.java:22)
 */
