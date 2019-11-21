package com.vilce.rabbitmq.stenes.callBack;

import com.vilce.rabbitmq.model.vo.MsgStatus;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.CallBackSender
 * @Author: 雷才哲
 * @Date: 2019/11/4 11:08
 * @Version: 1.0
 */
@Component
public class CallBackSender implements  RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public MsgStatus send() {
        MsgStatus msgStatus = new MsgStatus();
        rabbitTemplate.setConfirmCallback(this);
        String msg="callbackSender : i am callback sender";
        System.out.println(msg );
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("callbackSender UUID: " + correlationData.getId());
        try {
            this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg, correlationData);
            msgStatus.setMsgStatus("callBack消息发送成功！");
        }catch (AmqpException e){
            e.printStackTrace();
            msgStatus.setMsgStatus("callBack消息发送失败！");
        }
        return msgStatus;
    }
    
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("callbakck confirm: " + correlationData.getId());
    }
}