package com.vilce.rabbitmq.controller;

import com.vilce.rabbitmq.stenes.moreSendMoreReceiver.MoreSender1;
import com.vilce.rabbitmq.stenes.moreSendMoreReceiver.MoreSender2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.rabbitmq.controller.MoreSendMoreReceverController
 * @Author: 雷才哲
 * @Date: 2019/11/12 14:03
 * @Version: 1.0
 */
@RestController
@Api(tags = "多生产者-多消费者")
@RequestMapping("/more")
public class MoreSendMoreReceiverController {

    @Autowired
    private MoreSender1 moreSender1;
    @Autowired
    private MoreSender2 moreSender2;

    @ApiOperation(value = "多生产-多消费")
    @GetMapping("manyToMany")
    public void manyToMany(){
        for(int i=0;i<10;i++){
            moreSender1.send("hellomsg:"+i);
            moreSender2.send("hellomsg:"+i);
        }
    }
}
