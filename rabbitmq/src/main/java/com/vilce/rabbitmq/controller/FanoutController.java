package com.vilce.rabbitmq.controller;

import com.vilce.rabbitmq.model.vo.MsgStatus;
import com.vilce.rabbitmq.stenes.fanoutExChange.FanoutSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.FanoutController
 * @Author: 雷才哲
 * @Date: 2019/11/4 15:53
 * @Version: 1.0
 */
@RestController
@RequestMapping("/fanout")
@Api(tags = "广播模式或者订阅模式")
public class FanoutController {

    @Autowired
    private FanoutSender fanoutSender;

    @GetMapping(value = "/test")
    public MsgStatus fanoutTest() {
        return fanoutSender.send();
    }
}
