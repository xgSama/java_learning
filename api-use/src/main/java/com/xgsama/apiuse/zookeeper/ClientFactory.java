package com.xgsama.apiuse.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * ClientFactory
 *
 * @author : xgSama
 * @date : 2021/9/5 18:10:00
 */
public class ClientFactory {
    public static CuratorFramework createSimple(String zkHosts) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(zkHosts, retryPolicy);
    }

    public static CuratorFramework createWithOptions(String zkHosts, RetryPolicy retryPolicy,
                                                     int connectionTimeoutMs, int sessionTimeoutMs) {
        return CuratorFrameworkFactory.builder()
                .connectString(zkHosts)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }
}
