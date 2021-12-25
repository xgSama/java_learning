package com.xgsama.apiuse.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static com.xgsama.apiuse.kafka.KafkaConfig.*;

/**
 * ReceiveDemo
 *
 * @author : xgSama
 * @date : 2021/9/23 16:29:01
 */
public class ReceiveDemo {
    public static void main(String[] args) {

        String clientId = "dtstack";

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test_group_zy");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
//        kafkaConsumer.subscribe(Collections.singletonList(topic));
        kafkaConsumer.subscribe(Collections.singletonList("topic-demo"));
        System.out.println(kafkaConsumer.toString());


        while (true) {
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofSeconds(3L));
            for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                System.out.println(stringStringConsumerRecord.value());
            }
        }

    }
}
