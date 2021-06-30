package com.xgsama.java.concurrent.thread.create;

import java.util.concurrent.*;

/**
 * ThreadPool
 *
 * @author xgSama
 * @date 2021/6/27 19:19
 */
public class ThreadPool {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());


        Future<Long> future = executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                Thread.sleep(3000);
                System.out.println("异步任务开始。。。。");
                return 3L;
            }
        });

        Long result = future.get();
        System.out.println("异步任务结果：" + result);

        for (int i = 0; i < 5; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName() + "  -->  i");
                    }
                }
            });
        }

        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
