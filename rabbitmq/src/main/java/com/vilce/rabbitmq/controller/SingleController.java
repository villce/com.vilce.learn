package com.vilce.rabbitmq.controller;

import com.vilce.rabbitmq.stenes.single.SingleSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.SingleController
 * @Author: 雷才哲
 * @Date: 2019/11/12 16:20
 * @Version: 1.0
 */
@RestController
@Api(tags = "单生产者-单消费者")
@RequestMapping("/single")
public class SingleController {

    @Autowired
    private SingleSender singleSend;

    @ApiOperation("单生产单消费")
    @GetMapping(value = "/oneToOne")
    public void oneToOne() {
        for(int i=0;i<10;i++){
            singleSend.send();
        }
    }
}
