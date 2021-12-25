package com.xgsama.java.basis.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * XgThreadPool
 *
 * @author : xgSama
 * @date : 2021/12/18 13:06:08
 */
@Slf4j(topic = "main")
public class XgThreadPool {
    public static void main(String[] args) {
        ThreadPool1 threadPool = new ThreadPool1(2,
                1000, TimeUnit.MILLISECONDS, 10,
                new RejectPolicy<Runnable>() {
                    @Override
                    public void reject(BlockingQueue<Runnable> queue, Runnable task) {
                        // 死等
//                        queue.put(task);
                        // 超时等待
//                        queue.offer(task, 5, TimeUnit.SECONDS);
                        // 放弃执行
//                        log.warn("队列满了， 放弃执行， {}", task);
                        // 异常
                        throw new RuntimeException("队列满了");
                    }
                });

        for (int i = 0; i < 15; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                log.info("execute  {}", finalI);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

@Slf4j(topic = "threadPool")
class ThreadPool1 {
    private BlockingQueue<Runnable> taskQueue;

    private final HashSet<Worker> workers = new HashSet<>();

    private int coreSize;

    private RejectPolicy<Runnable> rejectPolicy;

    private long timeout;

    private TimeUnit unit;

    public ThreadPool1(int coreSize, long timeout, TimeUnit unit, int capacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        this.taskQueue = new BlockingQueue<>(capacity);
    }

    public ThreadPool1(int coreSize, long timeout, TimeUnit unit, int capacity, RejectPolicy rejectPolicy) {
        this(coreSize, timeout, unit, capacity);
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增 Worker {}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                log.debug("加入任务队列{}", task);
//                taskQueue.put(task);
                // 满？
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
//            while (task != null || (task = taskQueue.take()) != null) {
            while (task != null || (task = taskQueue.poll(5, TimeUnit.SECONDS)) != null) {
                try {
                    log.debug("正在执行。。。{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                log.debug("移除 Worker {}", this);
                workers.remove(this);
            }
        }
    }

}

@Slf4j(topic = "queue")
class BlockingQueue<T> {
    // 1. 任务队列
    private Deque<T> qeque = new ArrayDeque<>();

    // 2. 锁
    private ReentrantLock lock = new ReentrantLock();

    private Condition fullWaitSet = lock.newCondition();
    private Condition emptyWaitSet = lock.newCondition();

    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public T poll(long timeout, TimeUnit unit) {
        lock.lock();

        try {
            long nacos = unit.toNanos(timeout);
            while (qeque.isEmpty()) {
                try {
                    if (nacos <= 0) {
                        return null;
                    }
                    nacos = emptyWaitSet.awaitNanos(nacos);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = qeque.removeFirst();
            log.debug("从队列取出一个任务， {}", t);
            fullWaitSet.signal();

            return t;
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();

        try {
            while (qeque.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = qeque.removeFirst();
            log.debug("从队列取出一个任务， {}", t);
            fullWaitSet.signal();

            return t;
        } finally {
            lock.unlock();
        }
    }

    public boolean offer(T task, long timeout, TimeUnit unit) {
        lock.lock();

        try {
            long nacos = unit.toNanos(timeout);
            while (qeque.size() == capacity) {
                try {
                    log.warn("队列满了,等待加入。。。。。");
                    if (nacos <= 0) {
                        log.warn("超时未添加，放弃添加{}", task);
                        return false;
                    }
                    nacos = fullWaitSet.awaitNanos(nacos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            qeque.add(task);
            log.debug("加入任务队列， {}", task);

            emptyWaitSet.signal();

            return true;
        } finally {
            lock.unlock();
        }
    }

    public void put(T ele) {
        lock.lock();

        try {
            while (qeque.size() == capacity) {
                try {
                    log.warn("队列满了,等待加入。。。。。");
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            qeque.addLast(ele);

            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();

        try {
            //  队列是否已满
            if (qeque.size() >= capacity) {
                rejectPolicy.reject(this, task);
            } else {
                log.debug("加入任务队列， {}", task);
                qeque.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }


    public int getSize() {
        return qeque.size();
    }

}


interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}