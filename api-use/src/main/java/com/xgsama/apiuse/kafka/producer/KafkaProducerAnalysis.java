package com.xgsama.apiuse.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

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
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/xgSama/IdeaProjects/java_learning/input/borrow.txt"));


        String str = "";

        while ((str = bufferedReader.readLine()) != null) {
            RecordMetadata recordMetadata = producer.send(new ProducerRecord<>("topic-binlog", str)).get();

        }
    }
}
