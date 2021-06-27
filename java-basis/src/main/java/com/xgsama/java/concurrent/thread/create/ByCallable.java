package com.xgsama.java.concurrent.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * ByRunnable
 *
 * @author xgSama
 * @date 2021/6/27 16:59
 */
public class ByCallable {

    public static final int MAX_TRUE = 5;
    public static final int COMPUTE_TIME = 1000000;


    static class ThreadDemo implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " Started ...");
            Thread.sleep(1000);
            for (int i = 0; i < COMPUTE_TIME; i++) {
                int j = i * 10000;
            }

            long used = System.currentTimeMillis() - startTime;
            System.out.println(Thread.currentThread().getName() + " End ...");

            Thread.sleep(3000);
            return used;
        }
    }

    public static void main(String[] args) throws Exception {

        ThreadDemo task = new ThreadDemo();
        FutureTask<Long> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask, "returnableThread");
        thread.start();
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " 让子弹飞一会...");
        System.out.println(Thread.currentThread().getName() + " 做一点自己的事情...");
        for (int i = 0; i < COMPUTE_TIME; i++) {
            int j = i * 10000;
        }
        System.out.println(Thread.currentThread().getName() + " 获取并发任务的执行结果");
        try {
            System.out.println(thread.getName() + "线程占用时间：" + futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 运行结束...");

    }
}
