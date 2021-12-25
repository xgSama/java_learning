package com.xgsama.jvm.innerlock;

/**
 * LockDemo
 *
 * @author : xgSama
 * @date : 2021/12/3 14:32:52
 */
public class LockDemo {
    public static final Object obj = new Object();
    public static int i = 0;

    public static void main(String[] args) {
        add();
    }

    public static void add() {
        synchronized (obj) {
            i++;
        }
    }
}
