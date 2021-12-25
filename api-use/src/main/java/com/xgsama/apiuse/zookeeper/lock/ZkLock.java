package com.xgsama.apiuse.zookeeper.lock;

import com.xgsama.common.lock.DistributedLock;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ZkLock
 *
 * @author : xgSama
 * @date : 2021/11/13 19:21:45
 */
@Data
@Slf4j
public class ZkLock implements DistributedLock {
    // zkLock节点的连接
    private static final String ZK_PATH = "/test/lock";
    private static final String LOCK_PREFIX = ZK_PATH + "/lock-";
    private static final long WAIT_TIME = 1000L;

    // zk客户端
    CuratorFramework client = null;

    private String lockedShortPath = null;
    private String lockedPath = null;
    private String priorPath = null;
    final AtomicInteger lockCount = new AtomicInteger(0);
    private Thread thread;

    public ZkLock(CuratorFramework client) {
        this.client = client;
    }


    @Override
    public boolean lock() {
        // 可重入，确保同一线程可以重复加锁
        synchronized (this) {
            if (lockCount.get() == 0) {
                thread = Thread.currentThread();
                lockCount.incrementAndGet();
            } else {
                if (!thread.equals(Thread.currentThread())) {
                    return false;
                }
                lockCount.incrementAndGet();
                return true;
            }
        }

        try {
            boolean locked = false;
            // 首先尝试着去加锁
            locked = tryLock();

            if (locked) {
                return true;
            }
            // 如果加锁失败就去等待
            while (!locked) {
                await();

                // 获取等待的子节点列表
                List<String> waiters = getWaiters();
                // 判断，是否加锁成功
                if (checkLocked(waiters)) {
                    locked = true;
                }
            }
            return true;
        } catch (Exception e) {
            lockCount.decrementAndGet(); // 减少计数
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean tryLock() throws Exception {

        lockedPath = client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath(LOCK_PREFIX);

        if (lockedPath == null) {
            throw new RuntimeException("zk error");
        }

        //取得加锁的排队编号
        lockedShortPath = getShortPath(lockedPath);
        //然后获取所有节点
        List<String> waiters = getWaiters();

        //获取等待的子节点列表，判断自己是否第一个
        if (checkLocked(waiters)) {
            return true;
        }

        // 判断自己排第几个
        int index = Collections.binarySearch(waiters, lockedShortPath);
        if (index < 0) { // 网络抖动，获取到的子节点列表里可能已经没有自己了
            throw new Exception("节点没有找到: " + lockedShortPath);
        }

        //如果自己没有获得锁，则要监听前一个节点
        priorPath = ZK_PATH + "/" + waiters.get(index - 1);

        return false;
    }

    @Override
    public boolean unlock() {
        // 只有加锁的线程才能解锁
        if (!thread.equals(Thread.currentThread())) {
            return false;
        }
        // 减少可重入的计数
        int newLockCount = lockCount.decrementAndGet();
        // 计数不能小于0
        if (newLockCount < 0) {
            throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + lockedPath);
        }

        //如果计数不为0，直接返回
        if (newLockCount != 0) {
            return true;
        }

        //删除临时节点
        try {
            if (client.checkExists().forPath(lockedPath) != null) {
                client.delete().forPath(lockedPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean await() throws Exception {

        if (null == priorPath) {
            throw new Exception("prior_path error");
        }
        final CountDownLatch latch = new CountDownLatch(1);

        //订阅比自己次小顺序节点的删除事件
        Watcher watcher = watchedEvent -> {
            System.out.println("监听到的变化 watchedEvent = " + watchedEvent);
            log.info("[WatchedEvent]节点删除");

            latch.countDown();
        };
        client.getData().usingWatcher(watcher).forPath(priorPath);

        latch.await(WAIT_TIME, TimeUnit.SECONDS);


        return false;
    }

    private boolean checkLocked(List<String> waiters) {

        //节点按照编号，升序排列
        Collections.sort(waiters);

        // 如果是第一个，代表自己已经获得了锁
        if (lockedShortPath.equals(waiters.get(0))) {
            log.info("成功的获取分布式锁,节点为{}", lockedShortPath);
            return true;
        }
        return false;
    }


    private String getShortPath(String lockedPath) {

        int index = lockedPath.lastIndexOf(ZK_PATH + "/lock-");
        if (index >= 0) {
            index += ZK_PATH.length() + 1;
            return index <= lockedPath.length() ? lockedPath.substring(index) : "";
        }
        return null;
    }

    /**
     * 从zookeeper中拿到所有等待节点
     */
    protected List<String> getWaiters() {

        List<String> children = null;
        try {
            children = client.getChildren().forPath(ZK_PATH);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return children;
    }
}
