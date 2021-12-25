package com.xgsama.apiuse.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static com.xgsama.apiuse.kafka.KafkaConfig.initConfig;

/**
 * ResetProducer
 *
 * @author : xgSama
 * @date : 2021/12/8 10:12:19
 */
public class ResetProducer {

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        String str = "{\"Timestamp\": 12222222,\"Value\":1,\"timestamp\": 12222222,  \"value\":1}";

        for (int i = 0; i < 5; i++) {

            try {
                RecordMetadata recordMetadata = producer.send(new ProducerRecord<>("topic-reset", str)).get();
                System.out.println(recordMetadata);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

//            TimeUnit.MILLISECONDS.sleep(500);
        }

    }
}
