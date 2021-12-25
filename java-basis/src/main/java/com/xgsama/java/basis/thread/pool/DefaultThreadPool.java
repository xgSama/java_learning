package com.xgsama.java.basis.thread.pool;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * DefaultThreadPool
 *
 * @author : xgSama
 * @date : 2021/12/5 19:22:37
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;
    // 线程池默认线程数
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    // 线程池的最小数
    private static final int MIN_WORKER_NUMBERS = 1;

    // 工作列表
    private final LinkedList<Job> jobs = new LinkedList<>();
    // 工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private int workerNum = DEFAULT_WORKER_NUMBERS;

    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializedWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : Math.max(num, MIN_WORKER_NUMBERS);
        initializedWorkers(workerNum);
    }


    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notifyAll();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutDown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }

            initializedWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }

            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutDown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    public void initializedWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    // 工作者，复制消费任务
    class Worker implements Runnable {
        // 是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            Job job = null;
            while (running) {
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "等待添加任务");
                            jobs.wait();
                        } catch (InterruptedException e) {
                            // 感知外部对workerThread的中断操作
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    job.run();
                }
            }
        }


        public void shutDown() {
            running = false;
        }
    }


    public static void main(String[] args) {
        DefaultThreadPool<Runnable> runnableDefaultThreadPool = new DefaultThreadPool<>();
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 20; i++) {

            runnableDefaultThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+ "> hello" + count.incrementAndGet());
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
