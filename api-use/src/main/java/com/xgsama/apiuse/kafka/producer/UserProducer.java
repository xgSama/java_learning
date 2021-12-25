package com.xgsama.apiuse.kafka.producer;

import com.xgsama.apiuse.entity.User;
import com.xgsama.apiuse.kafka.UserSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.LeaderNotAvailableException;
import org.apache.kafka.common.errors.RecordTooLargeException;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static com.xgsama.apiuse.kafka.KafkaConfig.initConfig;
import static com.xgsama.apiuse.kafka.KafkaConfig.topic;

/**
 * UserProducer
 *
 * @author : xgSama
 * @date : 2021/11/1 23:27:51
 */
public class UserProducer {
    public static void main(String[] args) {
        Properties props = initConfig();
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserSerializer.class.getName());
        props.setProperty(ProducerConfig.RETRIES_CONFIG, "3");
        KafkaProducer<String, User> producer = new KafkaProducer<>(props);

        try {
            producer.send(new ProducerRecord<>("topic-demo", new User("cyz", 22, "男")), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {

                    if (metadata != null) {
                        System.out.println(metadata.partition() + " - " + metadata.offset());
                    }
                    if (exception != null) {
                        System.out.println("6666666");
                        exception.printStackTrace();
                    }
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println("5555");
        }

        while (true) {

        }

    }
}
