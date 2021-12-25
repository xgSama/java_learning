package com.xgsama.jvm.gc;

/**
 * ConstantPool
 *
 * @author : xgSama
 * @date : 2021/11/30 00:12:50
 */
public class ConstantPool {
    public static void main(String[] args) {
        String str = "abc";
        System.out.println(System.identityHashCode((str + Integer.toString(0))));
        System.out.println(System.identityHashCode((str + Integer.toString(0)).intern()));
//        System.gc();
        System.out.println(System.identityHashCode((str + Integer.toString(0)).intern()));
    }
}
