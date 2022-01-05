package com.xgsama.apiuse.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * APITest
 *
 * @author : xgSama
 * @date : 2021/11/8 17:11:31
 */
public class APITest {
    public static void main(String[] args) {
        Properties properties = KafkaConfig.initConfig();
        System.out.println(Integer.MAX_VALUE);

//        ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION

    }
}
