package com.xgsama.apiuse.kafka.flink;

import com.alibaba.fastjson.JSON;
import com.github.javafaker.Faker;
import com.xgsama.apiuse.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Locale;
import java.util.Properties;

import static com.xgsama.apiuse.kafka.KafkaConfig.initConfig;

/**
 * Table1
 *
 * @author : xgSama
 * @date : 2021/11/4 09:46:11
 */
@Slf4j
public class Table1 {

    private static final String mainTable = "zy_table_1";


    public static void main(String[] args) throws InterruptedException {
        Faker faker = new Faker(Locale.CHINA);
        int startId = 1001;
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String[] gender = {"男", "女"};

        while (true) {
            Student student = new Student();
            student.setId(startId++);
            student.setName(faker.name().name());
            student.setGender(gender[faker.number().numberBetween(0, 1)]);
            student.setAge(faker.number().numberBetween(10, 35));
            producer.send(new ProducerRecord<>(mainTable, JSON.toJSONString(student)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        log.error("发送失败!" + exception.toString());
                    }
                }
            });

            if (startId % 1000 == 0) {
                System.out.println(student.toString());
            }
//            TimeUnit.SECONDS.sleep(5);
        }
    }
}
