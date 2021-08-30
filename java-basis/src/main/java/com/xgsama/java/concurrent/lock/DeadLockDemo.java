package com.xgsama.java.concurrent.lock;

/**
 * DeadLockDemo
 *
 * @author : xgSama
 * @date : 2021/8/20 21:00:33
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (B) {
                    System.out.println("t1");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (A) {
                    System.out.println("t2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
