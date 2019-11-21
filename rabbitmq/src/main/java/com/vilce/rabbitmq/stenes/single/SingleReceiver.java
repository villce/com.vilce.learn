package com.vilce.rabbitmq.stenes.single;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.SingleReceiver
 * @Author: 雷才哲
 * @Date: 2019/11/12 16:23
 * @Version: 1.0
 */
@Component
@RabbitListener(queues = "singleQueue")
public class SingleReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("SingleReceiver1  : " + hello);
    }
}
