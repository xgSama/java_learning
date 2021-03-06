package com.xgsama.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * PhantomReferenceDemo
 *
 * @author xgSama
 * @date 2021/1/23 15:51
 */
public class PhantomReferenceDemo {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<RefTest> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<RefTest> phantomReference = new PhantomReference<>(new RefTest(), QUEUE);

//        System.out.println(phantomReference.get());


        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }

                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends RefTest> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("--- 虚引用对象被jvm回收了 ---" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
