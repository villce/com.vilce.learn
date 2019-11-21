package com.vilce.rabbitmq.stenes.delatTask;

import com.vilce.rabbitmq.config.RabbitConfig;
import com.vilce.rabbitmq.model.po.Msg;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.DelayReceiver
 * @Author: 雷才哲
 * @Date: 2019/11/4 9:37
 * @Version: 1.0
 */
@Component
public class DelayReceiver {

    @RabbitListener(queues = {RabbitConfig.PROCESS_QUEUE})
    public void process(Msg msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间: " + sdf.format(new Date()) + " ---> msg：[" + msg + "]");
    }
}
