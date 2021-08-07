package com.xgsama.java.concurrent.producerandconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NotSafeDataBuffer
 * 数据缓冲区，不安全版本的定义
 *
 * @author : xgSama
 * @date : 2021/8/3 11:56:38
 */
@Slf4j
public class NotSafeDataBuffer<T> {
    private static final int MAX_AMOUNT = 10;
    private List<T> dataList = new LinkedList<>();

    // 保存数量
    private AtomicInteger amount = new AtomicInteger(0);

    // 向数据区添加一个元素
    public void add(T element) throws Exception {
        if (amount.get() > MAX_AMOUNT) {
            log.error("队列已经满了");
            return;
        }

        dataList.add(element);
        log.info("添加一个元素：" + element);
        amount.incrementAndGet();

        if (amount.get() != dataList.size()) {
            throw new Exception(amount + "!=" + dataList.size());
        }
    }

    // 从数据区取出一个元素
    public T fetch() throws Exception {
        if (amount.get() <= 0) {
            log.error("队列已经空啦");
            return null;
        }

        T element = dataList.remove(0);
        log.info("删除一个元素：" + element);
        amount.decrementAndGet();

        if (amount.get() != dataList.size()) {
            throw new Exception("删除后：" + amount + "!=" + dataList.size());
        }

        return element;
    }
}
