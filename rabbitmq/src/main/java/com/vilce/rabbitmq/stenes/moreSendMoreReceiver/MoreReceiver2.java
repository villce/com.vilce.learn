package com.vilce.rabbitmq.stenes.moreSendMoreReceiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.MoreReceiver2
 * @Author: 雷才哲
 * @Date: 2019/11/12 14:11
 * @Version: 1.0
 */
@Component
@RabbitListener(queues = "moreQueue")
public class MoreReceiver2 {
    @RabbitHandler
    public void process(String hello) throws IOException {
        System.out.println("Receiver2  : " + hello);
    }
}
