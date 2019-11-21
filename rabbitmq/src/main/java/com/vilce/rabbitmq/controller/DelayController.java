package com.vilce.rabbitmq.controller;

import com.vilce.rabbitmq.model.po.Msg;
import com.vilce.rabbitmq.model.vo.MsgStatus;
import com.vilce.rabbitmq.stenes.delatTask.DelaySender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.DelayController
 * @Author: 雷才哲
 * @Date: 2019/10/31 9:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/delay")
@Api(tags = "延迟队列接口")
public class DelayController {
    @Autowired
    private DelaySender delaySender;


    @ApiOperation("延时队列发送(发送消息的时候设置过期时间)")
    @PostMapping(value = "/sendDelay")
    public MsgStatus sendDelayMsg(@RequestBody Msg msg) {
        return delaySender.sendDelayMsg(msg);
    }

    @ApiOperation("延时队列发送(整个队列设置过期时间，与msg没有关系)")
    @PostMapping(value = "/sendQueueDelay")
    public MsgStatus sendDelayQueueMsg(@RequestBody Msg msg) {
        return  delaySender.sendDelayQueue(msg);
    }

}
