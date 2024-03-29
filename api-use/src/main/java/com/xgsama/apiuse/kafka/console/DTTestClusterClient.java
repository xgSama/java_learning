package com.xgsama.apiuse.kafka.console;

import com.xgsama.apiuse.kafka.KafkaConfig;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.internals.Topic;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

/**
 * DTTestClusterClient
 *
 * @author : xgSama
 * @date : 2021/11/24 17:36:01
 */
public class DTTestClusterClient {
    private static AdminClient client = null;
    private static final String brokerList = "minio:9092";
//    private static final String brokerList = "172.16.20.21:9092,172.16.20.22:9092";


    static {

        Properties props = new Properties();
//        props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.brokerList);
        props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.setProperty(AdminClientConfig.CLIENT_ID_CONFIG, "admin-client-java");
        client = AdminClient.create(props);
    }


    public static void main(String[] args) throws Exception {
//        addTopics("zy-iceberg-userinfo");

        client.listTopics().names().get().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

//        client.deleteTopics(Collections.singletonList("topic-demo")).all().get();
//        resetOffset();
    }

    public static boolean addTopics(String... topics) {

        List<NewTopic> createTopics = new ArrayList<>();
        for (String topic : topics) {
            createTopics.add(new NewTopic(topic, 1, (short) 1));
        }

        CreateTopicsResult result = client.createTopics(createTopics);

        try {
            result.all().get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static void resetOffset() throws ExecutionException, InterruptedException {
        String groupId = "test_group_zy";
        String topic = "topic-demo";


        Map<TopicPartition, OffsetAndMetadata> map = new HashMap<>();

        TopicPartition topicPartition = new TopicPartition(topic, 0);
        OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(3);


        map.put(topicPartition, offsetAndMetadata);

        client.alterConsumerGroupOffsets(groupId, map).all().get();
    }
}
