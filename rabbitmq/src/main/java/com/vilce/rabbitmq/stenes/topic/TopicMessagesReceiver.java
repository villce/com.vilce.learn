package com.vilce.rabbitmq.stenes.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.TopicMessagesReceiver
 * @Author: 雷才哲
 * @Date: 2019/11/12 16:33
 * @Version: 1.0
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicMessagesReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessagesReceiver  : " +msg);
    }
}