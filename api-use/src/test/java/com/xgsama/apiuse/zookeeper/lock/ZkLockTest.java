package com.xgsama.apiuse.zookeeper.lock;

import com.xgsama.apiuse.zookeeper.ClientFactory;
import com.xgsama.common.concurrent.FutureTaskScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;

import static com.xgsama.common.config.CommonConfig.DT_TEST_ZK_HOST;

/**
 * ZkLockTest
 *
 * @author : xgSama
 * @date : 2021/11/14 11:53:28
 */
@Slf4j
public class ZkLockTest {
    int count = 0;




    @Test
    public void testLock() throws InterruptedException {
        CuratorFramework client = ClientFactory.createSimple(DT_TEST_ZK_HOST);

        client.start();
        ZkLock lock = new ZkLock(client);
        for (int i = 0; i < 100; i++) {
            FutureTaskScheduler.add(() -> {
                //创建锁
                lock.lock();
                //每条线程，执行10次累加
                for (int j = 0; j < 10; j++) {
                    //公共的资源变量累加
                    count++;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("count = " + count);
                //释放锁
                lock.unlock();

            });
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void lock1() {
        CuratorFramework client = ClientFactory.createSimple(DT_TEST_ZK_HOST);
        client.start();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                ZkLock lock = new ZkLock(client);
                lock.lock();
                for (int j = 0; j < 100; j++) {
                    count++;
                }
                log.info("count = " + count);
                lock.unlock();

            }).start();
        }


        while (true) {}
    }
}
