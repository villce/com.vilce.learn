package com.vilce.rabbitmq.stenes.oneSendMultiReceiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.OneSender
 * @Author: 雷才哲
 * @Date: 2019/11/12 15:39
 * @Version: 1.0
 */
@Component
public class OneSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        String sendMsg = msg + new Date();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("moreQueue", sendMsg);
    }
}
