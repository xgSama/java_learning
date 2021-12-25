package com.xgsama.apiuse.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.xgsama.common.config.CommonConfig.DT_TEST_ZK_HOST;

/**
 * Main
 *
 * @author : xgSama
 * @date : 2021/9/5 18:02:39
 */
@Slf4j
public class Main {
    public static final String zkHosts = "xgsama:2181";

    public static void main(String[] args) throws Exception {
        log.info(zkHosts);

        CuratorFramework client = ClientFactory.createSimple(DT_TEST_ZK_HOST);
        client.start();
        Stat stat = client.checkExists().forPath("/test/2");

        System.out.println(stat);
    }

    @Test
    public void createNode() {
        CuratorFramework zkClient = ClientFactory.createSimple(zkHosts);
        zkClient.start();
        String data = "hello";
        byte[] payload = data.getBytes(StandardCharsets.UTF_8);
        String zkPath = "/test/CRUD/node-1";

        try {

            Stat stat = zkClient.checkExists().forPath(zkPath);
            if (stat == null) {
                zkClient.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(zkPath, payload);
            } else {
                byte[] bytes = zkClient.getData().forPath(zkPath);
                String data1 = new String(bytes, StandardCharsets.UTF_8);
                log.info(data1);

                List<String> children = zkClient.getChildren().forPath("/test");
                for (String child : children) {
                    log.info("/test/" + child);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkClient.close();
        }
    }


    @Test
    public void create() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("xgsama:2181", new ExponentialBackoffRetry(1000, 3));

        client.start();
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/ztt");

    }


}
