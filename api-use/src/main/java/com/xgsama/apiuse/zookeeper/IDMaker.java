package com.xgsama.apiuse.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * IDMaker
 *
 * @author : xgSama
 * @date : 2021/11/13 16:36:14
 */
public class IDMaker {

//    String zkHosts = "xgsama:2181";
     String zkHosts = "172.16.101.50:2181,172.16.104.110:2181,172.16.104.111:2181";
    ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
    CuratorFramework client = ClientFactory.createSimple(zkHosts);


    /**
     * 创建顺序节点
     *
     * @param pathPrefix 节点路径
     * @return 创建后的完整路径
     */
    private String createSeqNode(String pathPrefix) {
        try {
            String fullPath = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(pathPrefix);

            return fullPath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public String makeId(String nodeName) {
        String seqNode = createSeqNode(nodeName);
        if (seqNode == null) {
            return null;
        }

        int index = seqNode.lastIndexOf(nodeName);

        if (index >= 0) {
            index += nodeName.length();
            return index <= nodeName.length() ? seqNode.substring(index) : "";
        }

        return null;
    }

    public static void main(String[] args) {
        IDMaker idMaker = new IDMaker();
        String nodeName = "/test/IdMaker/ID-";
        idMaker.client.start();

        for (int i = 0; i < 10; i++) {
            String id = idMaker.makeId(nodeName);
            System.out.println("第" + i + "个创建的id为：" + id);

        }
        idMaker.client.close();
    }
}
