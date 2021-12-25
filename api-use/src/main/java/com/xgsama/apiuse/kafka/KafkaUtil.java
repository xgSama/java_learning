package com.xgsama.apiuse.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * KafkaUtil
 *
 * @author : xgSama
 * @date : 2021/11/24 18:01:46
 */
public class KafkaUtil {

    public static final String brokerList = "172.16.101.50:9092,172.16.104.110:9092,172.16.104.111:9092";

    public static KafkaProducer<String, String> getTestKafkaProducer() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "admin-client-java");
        return new KafkaProducer<>(props);
    }
}
