package com.vilce.rabbitmq.stenes.moreSendMoreReceiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.MoreReceiver1
 * @Author: 雷才哲
 * @Date: 2019/11/12 14:10
 * @Version: 1.0
 */
@Component
@RabbitListener(queues = "moreQueue",containerFactory = "rabbitListenerContainerFactory")
public class MoreReceiver1 {
    @RabbitHandler
    public void process(String hello) throws IOException, InterruptedException {
        Thread.sleep(100);
        System.out.println("Receiver1  : " + hello);
    }
}
