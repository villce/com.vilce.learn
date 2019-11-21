package com.vilce.rabbitmq.stenes.single;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.rabbitmq.stenes.single.SingleSend
 * @Author: 雷才哲
 * @Date: 2019/11/12 16:22
 * @Version: 1.0
 */
@Component
public class SingleSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello1 " + new Date();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("singleQueue", sendMsg);
    }
}
