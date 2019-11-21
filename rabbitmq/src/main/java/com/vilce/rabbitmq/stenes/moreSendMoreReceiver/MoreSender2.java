package com.vilce.rabbitmq.stenes.moreSendMoreReceiver;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.rabbitmq.stenes.moreSendMoreReceiver.HelloMoreSender2
 * @Author: 雷才哲
 * @Date: 2019/11/12 14:08
 * @Version: 1.0
 */
@Component
public class MoreSender2 {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        String sendMsg = msg + new Date();
        System.out.println("Sender2 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("moreQueue", "Sender2 : " + sendMsg);
    }
}
