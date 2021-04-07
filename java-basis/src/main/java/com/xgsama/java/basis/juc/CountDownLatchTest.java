package com.xgsama.java.basis.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatchTest
 *
 * @author xgSama
 * @date 2021/4/7 11:15
 */
public class CountDownLatchTest {

    // 定义赛车的数量
    public final static int RACING_NUM = 50;

    public final static CountDownLatch countDownLatch = new CountDownLatch(RACING_NUM);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 1; i <= RACING_NUM; i++) {
            CountRunnable countRunnable = new CountRunnable(countDownLatch, i);
            pool.execute(countRunnable);
        }

        // 等待计数器归0然后向下执行
        countDownLatch.await();

        System.out.println("赛车以全部就绪");
        System.out.println("3!-2!-1!----go!!!!!!");
        pool.shutdown();
    }

    static class CountRunnable implements Runnable {
        private final CountDownLatch countDownLatch;
        private final int num;

        public CountRunnable(CountDownLatch countDownLatch, int num) {
            this.countDownLatch = countDownLatch;
            this.num = num;
        }

        @Override
        public void run() {

            System.out.println("第" + num + "辆赛车已就绪");

            //每次执行一次后-1
            countDownLatch.countDown();
        }
    }
}
