package com.xgsama.java.concurrent.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * PrintABCUseLock
 *
 * @author xgSama
 * @date 2020/11/5 12:42
 */
@SuppressWarnings("all")
public class PrintABCUseLock {
    private int times;
    private int state;
    private Lock lock = new ReentrantLock();

    public PrintABCUseLock(int times) {
        this.times = times;
    }

    public void printer(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.println(name);
            } else {
                System.out.println("------");
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final PrintABCUseLock loopThead = new PrintABCUseLock(1);

        new Thread(() -> {
            loopThead.printer("A", 0);
        }, "A").start();

        new Thread(() -> {
            loopThead.printer("B", 1);
        }, "B").start();

        new Thread(() -> {
            loopThead.printer("C", 2);
        }, "C").start();
    }
}
