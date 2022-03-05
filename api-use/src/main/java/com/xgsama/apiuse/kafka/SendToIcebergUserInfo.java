package com.xgsama.apiuse.kafka;

import com.alibaba.fastjson.JSONObject;
import com.xgsama.apiuse.entity.IcebergUserInfo;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * SendToIcebergUserInfo
 *
 * @author : xgSama
 * @date : 2022/2/22 11:46:19
 */
public class SendToIcebergUserInfo {
    static Properties props = null;
    private static final String brokerList = "172.16.20.21:9092,172.16.20.22:9092";


    static {

        props = new Properties();
        props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.setProperty(AdminClientConfig.CLIENT_ID_CONFIG, "admin-client-java");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        IcebergUserInfo icebergUserInfo =
                IcebergUserInfo.builder()
                        .id(10086L)
                        .name("cyz")
                        .age(24)
                        .height(999.99f)
                        .gender("男")
                        .birthday(LocalDate.of(1998, 8, 15))
                        .recordTime(LocalDateTime.now())
                        .build();

        String s = JSONObject.toJSONString(icebergUserInfo);
        producer.send(new ProducerRecord<>("zy-iceberg-userinfo", s)).get();
    }
}
