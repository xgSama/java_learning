package com.xgsama.java.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * PrintAbc
 *
 * @author : xgSama
 * @date : 2021/12/5 20:27:40
 */
public class PrintAbc {

    ReentrantLock lock = new ReentrantLock();

    int times;
    int state = 0;

    public PrintAbc(int times) {
        this.times = times;
    }


    public void printer(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.print(name + " ");

            }
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        PrintAbc printAbc = new PrintAbc(3);

        new Thread(() -> {
            printAbc.printer("A", 0);
        }).start();
        new Thread(() -> {
            printAbc.printer("B", 1);
        }).start();
        new Thread(() -> {
            printAbc.printer("C", 2);
        }).start();
    }
}
