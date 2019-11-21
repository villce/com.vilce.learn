package com.vilce.rabbitmq.stenes.delatTask;

import com.vilce.rabbitmq.config.RabbitConfig;
import com.vilce.rabbitmq.model.po.Msg;
import com.vilce.rabbitmq.model.vo.MsgStatus;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.DelaySender
 * @Author: 雷才哲
 * @Date: 2019/11/1 14:17
 * @Version: 1.0
 */
@Component
public class DelaySender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 在消息上设置时间
     * @param msg
     */
    public MsgStatus sendDelayMsg(Msg msg) {
        MsgStatus msgStatus = new MsgStatus();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(msg.getId() + " 延迟消息发送时间:" + sdf.format(new Date()));
        try {
            rabbitTemplate.convertAndSend(RabbitConfig.DELAY_EXCHANGE_NAME, "delay", msg, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration(msg.getTtl() + "");
                    return message;
                }
            });
            msgStatus.setMsgStatus("延迟消息发送成功");
        }catch (AmqpException e){
            e.printStackTrace();
            msgStatus.setMsgStatus("延迟消息发送失败");
        }
        return msgStatus;
    }

    public MsgStatus sendDelayQueue(Msg msg) {
        MsgStatus msgStatus = new MsgStatus();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(msg.getId() + " 延迟队列消息发送时间:" + sdf.format(new Date()));
        try {
            rabbitTemplate.convertAndSend(RabbitConfig.DELAY_QUEUE_EXCHANGE_NAME,"delay",  msg);
            msgStatus.setMsgStatus("延迟队列消息发送成功");
        }catch (AmqpException e){
            e.printStackTrace();
            msgStatus.setMsgStatus("延迟队列消息发送失败");
        }
        return msgStatus;
    }
}
