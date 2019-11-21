package com.vilce.rabbitmq.stenes.entityTransfer;

import com.vilce.rabbitmq.model.po.Msg;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.MsgSender
 * @Author: 雷才哲
 * @Date: 2019/11/4 13:24
 * @Version: 1.0
 */
@Component
public class MsgSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Msg msg) {
        System.out.println("msg send : " + msg.toString());
        this.rabbitTemplate.convertAndSend("msgQueue", msg);
    }
}
