package com.xgsama.apiuse.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * KafkaConfig
 *
 * @author : xgSama
 * @date : 2021/11/1 21:20:41
 */
public class KafkaConfig {
    public static final String brokerList = "172.16.101.50:9092,172.16.104.110:9092,172.16.104.111:9092";
    public static final String topic = "transaction-topic";

    public static Properties initConfig() {
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        return prop;
    }
}
