package com.xgsama.apiuse.zookeeper;

/**
 * SnowflakeIdGenerator
 *
 * @author : xgSama
 * @date : 2021/11/13 18:11:29
 */
public class SnowflakeIdGenerator {

    public static final Long MAX_WORKER_ID = (long) Integer.MAX_VALUE;

    private Long workerId;

    public static SnowflakeIdGenerator instance = new SnowflakeIdGenerator();

    public synchronized static void init(long workerId) {
        if (workerId > MAX_WORKER_ID) {
            throw new RuntimeException("workId is wrong: " + workerId);
        }
        instance.workerId = workerId;
    }

    private SnowflakeIdGenerator() {
    }

    ;
}
