package com.xgsama.java.concurrent.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SellTicket
 *
 * @author 霜风折叶
 * @version 1.0
 * @date 2020/10/11 15:32
 */
@SuppressWarnings("Convert2Lambda")
public class SellTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    ticket.sell();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                ticket.sell();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                ticket.sell();
            }
        }, "C").start();
    }
}

/**
 * 资源类
 */
class Ticket {
    private int number = 10000;

    private Lock lock = new ReentrantLock();

    public synchronized void sell() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() +
                    "-车票编号" + number-- +
                    ":剩余" + number + "张");
        } else {
            System.out.println("票已售完！");
        }
    }

    public void lockSell() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() +
                        "-车票编号" + number-- +
                        ":剩余" + number + "张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
