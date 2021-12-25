package com.xgsama.jvm.gc;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * GCTest
 *
 * @author : xgSama
 * @date : 2021/11/5 16:49:58
 */
public class GCTest {

    public static void main(String[] args) {
        // -XX:+UseParallelOldGC和-XX:+UseParallelGC结果一样，因为MXBean名字一样，但是实际使用的不一样
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : garbageCollectorMXBeans) {
            System.out.println(gc.getName());
            System.out.println("--");
        }
    }

}
