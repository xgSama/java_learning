package com.xgsama.apiuse.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

import java.nio.charset.StandardCharsets;

/**
 * SnowflakeIdWorker
 *
 * @author : xgSama
 * @date : 2021/11/13 18:00:20
 */
public class SnowflakeIdWorker {
    transient private CuratorFramework zkClient = null;
    // 工作节点的路径
    private String pathPrefix = "/test/IDMaker/worker-";
    private String pathRegister = null;

    // 保持节点id，不需要每次计算
    private Long nodeId = null;

    public static SnowflakeIdWorker instance = new SnowflakeIdWorker();

    private SnowflakeIdWorker() {
        zkClient = ClientFactory.createSimple("");
        this.init();
    }

    private void init() {
        try {
            byte[] payload = pathPrefix.getBytes(StandardCharsets.UTF_8);
            zkClient.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(pathPrefix, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取节点ID
    public Long getNodeId() {
        if (nodeId != null) {
            return nodeId;
        }
        String sid = null;

        if (pathRegister == null) {
            throw new RuntimeException("节点注册失败");
        }

        int index = pathRegister.lastIndexOf(pathPrefix);
        if (index > 0) {
            index += pathPrefix.length();
            sid = index <= pathRegister.length() ? pathRegister.substring(index) : null;
        }

        if (sid == null) {
            throw new RuntimeException("节点生成失败");
        }

        nodeId = Long.parseLong(sid);

        return nodeId;

    }
}
