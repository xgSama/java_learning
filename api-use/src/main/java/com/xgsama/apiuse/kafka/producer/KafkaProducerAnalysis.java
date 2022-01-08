package com.xgsama.apiuse.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.UUID;

import static com.xgsama.apiuse.kafka.KafkaConfig.*;

/**
 * KafkaProducerAnalysis
 *
 * @author : xgSama
 * @date : 2021/11/1 20:57:50
 */
public class KafkaProducerAnalysis {

    public static void main(String[] args) throws Exception {
        Properties props = initConfig();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.100.109:9092");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/xgSama/IdeaProjects/java_learning/input/borrow.txt"));


        String str = "";

        while ((str = bufferedReader.readLine()) != null) {
            RecordMetadata recordMetadata = producer.send(new ProducerRecord<>("zy-topic-demo", str)).get();

        }
    }
}
