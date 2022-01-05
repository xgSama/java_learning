package com.xgsama.apiuse.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * AliKafka
 *
 * @author : xgSama
 * @date : 2021/12/27 14:24:29
 */
public class AliKafka {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.103.218.168:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "idea");

//        prop.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
//        prop.setProperty(ProducerConfig.RETRIES_CONFIG, String.valueOf(Integer.MAX_VALUE));
//        prop.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
        prop.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");


        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);

        for (int i = 0; i < 3; i++) {
            RecordMetadata recordMetadata = producer.send(new ProducerRecord<>("test", "hello")).get();
            System.out.println(recordMetadata.topic() + ":" + ":" + recordMetadata.partition() + recordMetadata.offset());
        }

    }
}
