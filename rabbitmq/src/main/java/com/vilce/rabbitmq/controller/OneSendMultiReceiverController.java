package com.vilce.rabbitmq.controller;

import com.vilce.rabbitmq.stenes.oneSendMultiReceiver.OneSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.OneSendMultiReceiverController
 * @Author: 雷才哲
 * @Date: 2019/11/12 15:35
 * @Version: 1.0
 */
@RestController
@Api(tags = "单生产者-多消费者")
@RequestMapping("/one")
public class OneSendMultiReceiverController {

    @Autowired
    private OneSender oneSender;

    @ApiOperation(value = "单生产多消费")
    @GetMapping("oneToMulti")
    public void oneToMulti(){
        for(int i=0;i<20;i++){
            oneSender.send("hellomsg:"+i);
        }
    }

}
