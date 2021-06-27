package com.xgsama.java.concurrent.thread.create;

/**
 * ByRunnable
 *
 * @author xgSama
 * @date 2021/6/27 16:59
 */
public class ByRunnable {
    static class ThreadDemo implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "  -->  i");

            }
        }
    }

    public static void main(String[] args) {

        new Thread(new ThreadDemo()).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "  -->  i");
            }
        }).start();

    }
}
