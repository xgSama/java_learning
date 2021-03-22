package com.xgsama.java.basis.thread;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalDemo
 *
 * @author xgSama
 * @date 2021/1/23 13:19
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<Long> tl = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
       new Thread(() -> {
           tl.set(666L);
           System.out.println(tl.get());
       }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();
    }


}



