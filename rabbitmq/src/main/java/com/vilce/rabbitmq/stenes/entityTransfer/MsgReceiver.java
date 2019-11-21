package com.vilce.rabbitmq.stenes.entityTransfer;

import com.vilce.rabbitmq.model.po.Msg;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.MsgReceiver
 * @Author: 雷才哲
 * @Date: 2019/11/4 13:37
 * @Version: 1.0
 */
@Component
@RabbitListener(queues = "msgQueue")
public class MsgReceiver {

    @RabbitHandler
    public void process(Msg msg) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("user receive  : " + "id->"+msg.getId()+", content->"+msg.getContent());
    }

}
