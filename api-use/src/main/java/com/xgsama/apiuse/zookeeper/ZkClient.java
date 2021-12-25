package com.xgsama.apiuse.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * ZkClient
 *
 * @author : xgSama
 * @date : 2021/9/5 22:10:40
 */
@Slf4j
public class ZkClient {
    private static CuratorFramework zkClient;

    public static final Charset utf8 = StandardCharsets.UTF_8;

    public static ZkClient getInstance(String zkHosts) {
        return new ZkClient(zkHosts);
    }

    public static ZkClient getInstance(String zkHosts, RetryPolicy retryPolicy,
                                       int connectionTimeoutMs, int sessionTimeoutMs) {
        return new ZkClient(zkHosts, retryPolicy, connectionTimeoutMs, sessionTimeoutMs);
    }

    private ZkClient(String zkHosts) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        zkClient = CuratorFrameworkFactory.newClient(zkHosts, retryPolicy);
    }

    private ZkClient(String zkHosts, RetryPolicy retryPolicy,
                     int connectionTimeoutMs, int sessionTimeoutMs) {
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(zkHosts)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();

    }

    public String getHosts() {
        return zkClient.getZookeeperClient().getCurrentConnectionString();
    }

    public RetryPolicy getRetryPolicy() {
        return zkClient.getZookeeperClient().getRetryPolicy();
    }


    public boolean createNode(String path) throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat != null) {
            throw new RuntimeException("节点已存在");
        } else {
            String s = zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
            System.out.println(s);
        }

        return true;
    }

    public boolean updateNode(String path, String newData) throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            throw new RuntimeException("节点不存在");
        }

        zkClient.setData().forPath(path, newData.getBytes(utf8));
        return true;
    }


    public static List<String> readNode(String path) throws Exception {
        List<String> result = new ArrayList<>();
        byte[] bytes = zkClient.getData().forPath(path);
        if (bytes.length > 0) {
            result.add(new String(bytes, utf8));
        }
        List<String> children = zkClient.getChildren().forPath(path);
        for (String child : children) {
            result.add(path + "/" + child);
        }
        return result;

    }
}
