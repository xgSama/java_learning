package com.xgsama.java.concurrent.cas;

import com.xgsama.common.crazy.util.Print;
import com.xgsama.common.crazy.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

import static com.xgsama.common.crazy.util.ThreadUtil.sleepMilliSeconds;

/**
 * ABA
 *
 * @author : xgSama
 * @date : 2021/8/8 11:43:03
 */
public class ABA {
    @Test
    public void testAtomicStampedReference() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        AtomicStampedReference<Integer> aba = new AtomicStampedReference<>(1, 0);

        ThreadUtil.getMixedTargetThreadPool().submit(new Runnable() {
            @Override
            public void run() {

                boolean success = false;
                int stamp = aba.getStamp();
                Print.tco("before sleep 500: value=" + aba.getReference()
                        + " stamp=" + aba.getStamp());

                //等待500毫秒
                sleepMilliSeconds(500);
                success = aba.compareAndSet(1, 10,
                        stamp, ++stamp);

                Print.tco("after sleep 500 cas 1: success=" + success
                        + " value=" + aba.getReference()
                        + " stamp=" + aba.getStamp());


                //增加标记值
//                stamp++;
                success = aba.compareAndSet(10, 1,
                        stamp, ++stamp);
                Print.tco("after  sleep 500 cas 2: success=" + success
                        + " value=" + aba.getReference()
                        + " stamp=" + aba.getStamp());

                latch.countDown();
            }

        });

        ThreadUtil.getMixedTargetThreadPool().submit(new Runnable() {
            @Override
            public void run() {
                boolean success = false;
                int stamp = aba.getStamp();
                // stamp = 0
                Print.tco("before sleep 1000: value=" + aba.getReference()
                        + " stamp=" + aba.getStamp());

                //等待1000毫秒
                sleepMilliSeconds(1000);
                //stamp = 1
                Print.tco("after sleep 1000: stamp = " + aba.getStamp());

                // stamp = 1
                success = aba.compareAndSet(1, 20, stamp, ++stamp);
                Print.tco("after cas 3 1000: success=" + success
                        + " value=" + aba.getReference()
                        + " stamp=" + aba.getStamp());
                latch.countDown();
            }
        });
        latch.await();


    }

    @Test
    public void testAtomicMarkableReference() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);

        AtomicMarkableReference<Integer> atomicRef =
                new AtomicMarkableReference<>(1, false);

        ThreadUtil.getMixedTargetThreadPool().submit(() -> {
            boolean success = false;
            int value = atomicRef.getReference();
            boolean mark = getMark(atomicRef);
            Print.tco("before sleep 500: value=" + value
                    + " mark=" + mark);

            //等待500毫秒
            sleepMilliSeconds(500);
            success = atomicRef.compareAndSet(1, 10,
                    mark, !mark);

            Print.tco("after sleep 500 cas 1: success=" + success
                    + " value=" + atomicRef.getReference()
                    + " mark=" + getMark(atomicRef));


            latch.countDown();
        });

        ThreadUtil.getMixedTargetThreadPool().submit(() -> {
            boolean success = false;
            int value = atomicRef.getReference();
            boolean mark = getMark(atomicRef);
            Print.tco("before sleep 1000: value=" + atomicRef.getReference()
                    + " mark=" + mark);

            //等待1000毫秒
            sleepMilliSeconds(1000);
            Print.tco("after sleep 1000: mark = " + getMark(atomicRef));
            success = atomicRef.compareAndSet(1, 20, mark, !mark);
            Print.tco("after cas 3 1000: success=" + success
                    + " value=" + atomicRef.getReference()
                    + " mark=" + getMark(atomicRef));
            latch.countDown();
        });
        latch.await();
    }

    private boolean getMark(AtomicMarkableReference<Integer> atomicRef) {
        boolean[] markHolder = {false};
        int value = atomicRef.get(markHolder);
        return markHolder[0];
    }

}
