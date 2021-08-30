package com.xgsama.java.concurrent.producerandconsumer;

import com.xgsama.common.crazy.util.JvmUtil;
import com.xgsama.common.crazy.util.Print;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CommunicatePetStore
 *
 * @author : xgSama
 * @date : 2021/8/7 11:13:06
 */
public class CommunicatePetStore {

    private static final int MAX_AMOUNT = 10;

    static class DataBuffer<T> {
        // 保存数据
        private final List<T> dataList = new LinkedList<>();
        // 数据缓冲区长度
        private volatile Integer amount = 0;

        private final Object LOCK_OBJECT = new Object();
        private final Object NOT_FULL = new Object();
        private final Object NOT_EMPTY = new Object();

        // 向数据区添加一个元素
        public void add(T element) throws Exception {
            while (amount > MAX_AMOUNT) {
                synchronized (NOT_FULL) {
                    Print.tcfo("队列已经满了！");
                    NOT_FULL.wait();
                }

            }

            synchronized (LOCK_OBJECT) {
                if (amount < MAX_AMOUNT) {
                    dataList.add(element);
                    amount++;
                }
            }

            synchronized (NOT_EMPTY) {
                NOT_EMPTY.notify();
            }
        }

        // 从数据区取出一个元素
        public T fetch() throws Exception {
            while (amount <= 0) {
                synchronized (NOT_EMPTY) {
                    Print.tcfo("队列已经空了！");
                    // 等待未空通知
                    NOT_EMPTY.wait();
                }
            }

            T element = null;
            synchronized (LOCK_OBJECT) {
                if (amount > 0) {
                    element = dataList.remove(0);
                }
                amount--;
            }

            synchronized (NOT_FULL) {
                // 发送未满通知
                NOT_FULL.notify();
            }

            return element;
        }


    }

    public static void main(String[] args) {
        Print.cfo("当前的进程ID是：" + JvmUtil.getProcessID());
        // 共享数据区
        DataBuffer<IGoods> dataBuffer = new DataBuffer<>();
        // 生产者执行的动作
        Callable<IGoods> produceAction = () -> {
            IGoods goods = Goods.produceOne();
            dataBuffer.add(goods);
            return goods;
        };

        //  消费者执行的动作
        Callable<IGoods> consumerAction = () -> {
            IGoods goods = null;
            goods = dataBuffer.fetch();
            return goods;
        };

        final int THREAD_NUM = 20;
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);

        final int CONSUMER_TOTAL = 11;
        final int PRODUCER_TOTAL = 5;

        for (int i = 0; i < PRODUCER_TOTAL; i++) {
            threadPool.submit(new Producer(produceAction, 50));
        }
        for (int i = 0; i < CONSUMER_TOTAL; i++) {
            threadPool.submit(new Consumer(consumerAction, 100));
        }

    }
}
