package com.xgsama.apiuse.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * RocketClient
 *
 * @author : xgSama
 * @date : 2021/9/18 14:14:42
 */
@Slf4j
public class RocketClient {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_demo");
        //指定NameServer地址
        producer.setNamesrvAddr("172.16.104.76:9876");
        producer.start();

        for (int i = 0; i < 1; i++) {
            try {
                //构建消息
                Message msg = new Message("TopicTest" /* Topic */,
                        "TagA" /* Tag */,
                        ("测试RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );

                //发送同步消息
                SendResult sendResult = producer.send(msg);

                System.out.println(i);
                System.out.printf( "%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }


        producer.shutdown();
    }
}
