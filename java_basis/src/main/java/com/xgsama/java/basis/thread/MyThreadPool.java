package com.xgsama.java.basis.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * MyThreadPool
 *
 * @author xgSama
 * @date 2021/1/21 17:25
 */
public class MyThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    /**
     * 最大的线程数       默认10
     */
    private static final int MAX_WORKER_NUMBERS = 10;

    /**
     * 默认创建的线程数
     */
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    /**
     * 最小的线程数
     */
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     * 工作列表，需要向里面插入工作任务
     */
    private final LinkedList<Job> jobs = new LinkedList<>();

    /**
     * 工作者列表  利用自带的容器同步工具，也就是有多少个线程可以处理任务咯
     */
    private final List<MyWorker> workers = Collections.synchronizedList(new ArrayList<>());

    /**
     * 工作线程的数量
     */
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    /**
     * 生成线程编号
     */
    private AtomicLong threadNum = new AtomicLong();

    public void initWorkers(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : Math.max(num, MIN_WORKER_NUMBERS);
    }

    /**
     * 执行一个工作任务，将任务提交给线程池，这里直接用synchronized锁定同步容器
     *
     * @param job 任务
     */
    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    /**
     * 关闭线程池
     */
    @Override
    public void shutdown() {
        for (MyWorker worker : workers) {
            worker.shutdown();
        }

    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num) {

    }

    @Override
    public int getJobSize() {
        return 0;
    }

    /**
     * 定义自己的工作线程
     */
    class MyWorker implements Runnable {

        /**
         * 定义一个boolean类型的数据表示是否运行中……
         */
        private volatile boolean running = true;

        @Override
        public void run() {
            // 判断线程是否处于运行状态，如果运行则说明可以做后续工作
            while (running) {
                Job job = null;
                // 直接锁住整个工作队列
                synchronized (jobs) {
                    // 如果工作队列为空，则需要等待
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // 如果工作队列不为空，则取出任务
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    job.run();
                }

            }

        }

        /**
         * 暂停工作
         */
        public void shutdown() {
            running = false;
        }
    }
}
