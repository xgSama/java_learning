package com.xgsama.java.basis.thread.pool;

/**
 * ThreadPool
 *
 * @author xgSama
 * @date 2021/1/21 17:23
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 执行一个工作任务，将任务提交给线程池
     *
     * @param job 任务
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作线程
     *
     * @param num 增加的个数
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     *
     * @param num 减少的个数
     */
    void removeWorkers(int num);

    /**
     * 获取正在等待执行的任务数量
     *
     * @return
     */
    int getJobSize();
}
