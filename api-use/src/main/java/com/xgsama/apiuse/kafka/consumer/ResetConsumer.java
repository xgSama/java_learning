package com.xgsama.apiuse.kafka.consumer;

import com.xgsama.apiuse.kafka.UserDeserializer;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.xgsama.apiuse.kafka.KafkaConfig.brokerList;

/**
 * ResetConsumer
 *
 * @author : xgSama
 * @date : 2021/12/8 10:00:15
 */
public class ResetConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "reset-consumer");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "zy_stream");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "idea");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);


//        consumer.subscribe(Collections.singletonList("topic-reset"));
        consumer.subscribe(Collections.singletonList("created_by_zk"));



        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));

            if (records.isEmpty()) {
                continue;
            }

            records.forEach(record -> System.out.println(record.offset() + " " + record.value()));
        }

    }
}
