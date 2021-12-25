package com.xgsama.apiuse.kafka.consumer;

import com.xgsama.apiuse.entity.User;
import com.xgsama.apiuse.kafka.UserDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static com.xgsama.apiuse.kafka.KafkaConfig.*;

/**
 * UserConsumer
 *
 * @author : xgSama
 * @date : 2021/11/8 17:44:31
 */
public class UserConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserDeserializer.class.getName());


        KafkaConsumer<String, User> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singletonList(topic));

        while (true) {
            ConsumerRecords<String, User> poll = kafkaConsumer.poll(Duration.ofSeconds(3L));
            for (ConsumerRecord<String, User> stringStringConsumerRecord : poll) {
                System.out.println(stringStringConsumerRecord.value());
            }
        }

    }
}
