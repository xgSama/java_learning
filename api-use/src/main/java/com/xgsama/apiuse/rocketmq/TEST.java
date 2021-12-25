package com.xgsama.apiuse.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * TEST
 *
 * @author : xgSama
 * @date : 2021/9/18 16:13:59
 */
public class TEST {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_demo");
//指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr("172.16.104.76:9876"); //修改为自己的

/**
 * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
 * 如果非第一次启动，那么按照上次消费的位置继续消费
 */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                try {
                    for (MessageExt msg : msgs) {
                        String msgbody = new String(msg.getBody(), "utf-8");
                        System.out.println(" MessageBody: " + msgbody);//输出消息内容
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            }
        });


        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
