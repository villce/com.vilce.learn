package com.vilce.rabbitmq.controller;

import com.vilce.rabbitmq.stenes.topic.TopicSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.TopicExchangeController
 * @Author: 雷才哲
 * @Date: 2019/11/12 16:29
 * @Version: 1.0
 */
@RestController
@RequestMapping("/topic")
@Api(tags = "topic exchange类型rabbitmq")
public class TopicExchangeController {

    @Autowired
    private TopicSender topicSender;

    @ApiOperation(value = "topic测试")
    @GetMapping(value = "/test")
    public void topicTest() {
        topicSender.send();
    }
}
