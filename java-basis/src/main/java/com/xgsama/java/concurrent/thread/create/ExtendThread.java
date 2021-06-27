package com.xgsama.java.concurrent.thread.create;

/**
 * ExtendThread
 *
 * @author xgSama
 * @date 2021/6/27 16:53
 */
public class ExtendThread {

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + "  -->  i");
            }
            System.out.println("End.....");
        }
    }

    public static void main(String[] args) {

        Thread thread = new ThreadDemo();
        thread.start();
    }
}
