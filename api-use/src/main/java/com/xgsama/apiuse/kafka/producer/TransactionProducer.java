package com.xgsama.apiuse.kafka.producer;

import com.xgsama.apiuse.kafka.KafkaUtil;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * TransactionProducer
 *
 * @author : xgSama
 * @date : 2021/11/24 17:27:10
 */
public class TransactionProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaUtil.brokerList);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "admin-client-java");
        props.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "dt-20211124");
        props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        int start = 0;


        producer.initTransactions();

        while (true) {
            producer.beginTransaction();

            try {
                Map<TopicPartition, OffsetAndMetadata> commits = new HashMap<>();
                for (int i = 0; i < 100; i++) {
                    ProducerRecord<String, String> record = new ProducerRecord<>("transaction-topic", "transaction-" + ++start);
                    RecordMetadata recordMetadata = producer.send(record).get();
                    commits.put(new TopicPartition(recordMetadata.topic(), recordMetadata.partition()), new OffsetAndMetadata(recordMetadata.offset()));

                    if (start == 113) {
                        int j = 4 / 0;
                    }
                }
                producer.sendOffsetsToTransaction(commits, "transaction-consumer");
                producer.commitTransaction();
                TimeUnit.SECONDS.sleep(10);

            } catch (Exception e) {
                e.printStackTrace();
                producer.abortTransaction();
                return;
            }


        }
    }


}
