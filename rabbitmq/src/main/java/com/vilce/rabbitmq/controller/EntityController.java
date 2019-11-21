package com.vilce.rabbitmq.controller;

import com.vilce.rabbitmq.model.po.Msg;
import com.vilce.rabbitmq.stenes.entityTransfer.MsgSender;
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
 * @Package: com.vilce.EntityController
 * @Author: 雷才哲
 * @Date: 2019/11/4 13:22
 * @Version: 1.0
 */
@RestController
@RequestMapping("/entity")
@Api(tags = "实体传输接口")
public class EntityController {

    @Autowired
    private MsgSender msgSender;

    @ApiOperation("实体类传输测试")
    @PostMapping(value = "/test")
    public void userTest(@RequestBody Msg msg) {
        for(int i=0;i<=100;i++){
           msgSender.send(msg);
        }
    }
}