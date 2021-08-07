package com.xgsama.java.concurrent.crazy;

import java.util.concurrent.CountDownLatch;

/**
 * NotSafePlus
 *
 * @author : xgSama
 * @date : 2021/8/5 11:26:22
 */
public class NotSafePlus {
    private long amount = 0;

    public void add() {
        amount++;
    }

    public long get() {
        return amount;
    }

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 10, addTime = 10000;
        NotSafePlus data = new NotSafePlus();
        CountDownLatch latch = new CountDownLatch(10);

        Runnable counter = () -> {
            for (int i = 0; i < addTime; i++) {
                data.add();
            }
            latch.countDown();
        };

        for (int i = 0; i < threadNum; i++) {
            new Thread(counter).start();
        }

        latch.await();
        System.out.println("理论结果：" + threadNum * addTime);
        System.out.println("实际结果：" + data.get());
    }
}
