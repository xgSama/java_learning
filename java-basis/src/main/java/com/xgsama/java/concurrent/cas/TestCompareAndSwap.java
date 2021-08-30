package com.xgsama.java.concurrent.cas;

import com.xgsama.common.crazy.util.JvmUtil;
import com.xgsama.common.crazy.util.Print;
import com.xgsama.common.crazy.util.ThreadUtil;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TestCompareAndSwap
 *
 * @author : xgSama
 * @date : 2021/8/8 10:31:22
 */
public class TestCompareAndSwap {

    // 基于CAS无锁实现的安全自增
    static class OptimisticLockingPlus {
        private static final int THREAD_COUNT = 10;

        private volatile int value;

        private static final Unsafe unsafe = JvmUtil.getUnsafe();
        // value的内存偏移（相对于对象头部的偏移，不是绝对偏移）
        private static final long valueOffset;

        private static final AtomicLong failure = new AtomicLong(0L);

        static {
            try {
                // 获取value属性的内存偏移
                valueOffset = unsafe.objectFieldOffset(
                        OptimisticLockingPlus.class.getDeclaredField("value"));
                Print.tco("valueOffset = " + valueOffset);
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        public final boolean unsafeCompareAndSet(int oldValue, int newValue) {
            return unsafe.compareAndSwapInt(this, valueOffset, oldValue, newValue);
        }

        public void add() {
            int oldValue = value;
            int i = 0;
            do {
                oldValue = value;
                if (i++ > 1) {
                    failure.incrementAndGet();
                }
            } while (!unsafeCompareAndSet(oldValue, oldValue + 1));
        }

        public long getF() {
            return failure.get();
        }

        public static void main(String[] args) throws InterruptedException {
            final OptimisticLockingPlus cas = new OptimisticLockingPlus();
            CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
            for (int i = 0; i < THREAD_COUNT; i++) {
                ThreadUtil.getMixedTargetThreadPool().submit(() -> {
                    for (int j = 0; j < 10000; j++) {
                        cas.add();
                    }
                    latch.countDown();
                });
            }
            latch.await();
            Print.tco("累加之和：" + cas.value);
            Print.tco("失败次数" + cas.getF());
        }
    }

    @Test
    public void printObjectStruct() {
        OptimisticLockingPlus obj = new OptimisticLockingPlus();
        obj.value = 100;
        String printable = ClassLayout.parseInstance(obj).toPrintable();
        Print.fo("obj" + printable);
    }

}
