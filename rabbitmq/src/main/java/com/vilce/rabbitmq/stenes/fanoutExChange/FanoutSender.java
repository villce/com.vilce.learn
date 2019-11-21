package com.vilce.rabbitmq.stenes.fanoutExChange;

import com.vilce.rabbitmq.model.vo.MsgStatus;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.FanoutSender
 * @Author: 雷才哲
 * @Date: 2019/11/4 15:55
 * @Version: 1.0
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public MsgStatus send() {
        MsgStatus msgStatus = new MsgStatus();
        String msgString="fanoutSender :hello i am fanout";
        System.out.println(msgString);
        try {
            this.rabbitTemplate.convertAndSend("fanoutExchange","abcd.ee", msgString);
            msgStatus.setMsgStatus("广播信息发送成功！");
        }catch (AmqpException e){
            e.printStackTrace();
            msgStatus.setMsgStatus("广播消息发送失败！");
        }
        return msgStatus;
    }
}