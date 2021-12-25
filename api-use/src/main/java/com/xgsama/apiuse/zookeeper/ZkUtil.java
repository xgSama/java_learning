package com.xgsama.apiuse.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * ZkUtil
 *
 * @author : xgSama
 * @date : 2021/9/5 18:48:03
 */
public class ZkUtil {
    public static final Charset utf8 = StandardCharsets.UTF_8;

    public static boolean createNode(CuratorFramework zkClient, String path) throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat != null) {
            throw new RuntimeException("节点已存在");
        } else {
            String s = zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
            System.out.println(s);
        }

        return true;
    }

    public static boolean updateNode(CuratorFramework zkClient, String path, String newData) throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            throw new RuntimeException("节点不存在");
        }

        zkClient.setData().forPath(path, newData.getBytes(utf8));
        return true;
    }

    public static List<String> readNode(CuratorFramework zkClient, String path) throws Exception {
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
