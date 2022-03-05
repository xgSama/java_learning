package com.xgsama.apiuse.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.xgsama.apiuse.kafka.KafkaConfig.initConfig;

/**
 * SendDemo
 *
 * @author : xgSama
 * @date : 2021/9/23 16:26:34
 */
public class SendDemo {

    private static final String brokerList = "172.16.20.21:9092,172.16.20.22:9092";
    private static final String topic = "flink_test_data";


    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) throws Exception {
        Properties props = initConfig();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.brokerList);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        BufferedReader bufferedReader = new BufferedReader(new FileReader("input/kafka-send.txt"));

        String str = "{\"user_id\":\"cyz\",\"click_time\":199}";

//        while ((str = bufferedReader.readLine()) != null) {
//            RecordMetadata recordMetadata = producer.send(new ProducerRecord<>(topic, str)).get();
//            TimeUnit.SECONDS.sleep(1);
//        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        while (true) {


            String str1 = "{\"user_id\":\"cyz\",\"click_time\": \"" + formatter.format(LocalDateTime.now(ZoneId.of("UTC+8"))) + "\"}";


            RecordMetadata recordMetadata = producer.send(new ProducerRecord<>(topic, str1)).get();

            TimeUnit.SECONDS.sleep(1);
        }
    }
}
