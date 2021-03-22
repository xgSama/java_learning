package com.xgsama.java.basis.thread;

import java.util.concurrent.*;

/**
 * CreateThreadTest
 *
 * @author xgSama
 * @date 2021/1/19 11:46
 */
public class CreateThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread thread1 = new MyThread();
        thread1.start();

        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();

        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread3 = new Thread(futureTask);
        thread3.start();
        Integer integer = futureTask.get();
        System.out.println(integer);

        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.execute(() ->
                    System.out.println(Thread.currentThread() + "created by Executors"));
        }
        es.shutdown();

    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread() + "created by Thread");
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + "created by Runnable");
    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println(Thread.currentThread() + "created by Callable ");
        return 666;
    }
}
