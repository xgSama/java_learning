package com.xgsama.java.concurrent.producerandconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NotSafePetStore
 *
 * @author : xgSama
 * @date : 2021/8/3 11:32:02
 */
@Slf4j
public class NotSafePetStore {

    private static NotSafeDataBuffer<IGoods> notSafeDataBuffer = new NotSafeDataBuffer<>();

    static Callable<IGoods> produceAction = () -> {
        IGoods goods = Goods.produceOne();

        try {
            notSafeDataBuffer.add(goods);
        } catch (Exception e) {
            log.error("NotSafePetStore.produceAction：" + e);
        }

        return goods;
    };

    static Callable<IGoods> consumerAction = () -> {
        IGoods goods = null;

        try {
            goods = notSafeDataBuffer.fetch();
        } catch (Exception e) {
            log.error("NotSafePetStore.consumerAction", e);
        }

        return goods;
    };

    public static void main(String[] args) {

        final int THREAD_TOTAL = 20;
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_TOTAL);

        for (int i = 0; i < 5; i++) {
            threadPool.submit(new Producer(produceAction, 500));
            threadPool.submit(new Consumer(consumerAction, 200));
        }
    }
}