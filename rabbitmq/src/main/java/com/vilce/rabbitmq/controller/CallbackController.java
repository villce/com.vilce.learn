package com.vilce.rabbitmq.controller;

import com.vilce.rabbitmq.model.vo.MsgStatus;
import com.vilce.rabbitmq.stenes.callBack.CallBackSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.CallbackController
 * @Author: 雷才哲
 * @Date: 2019/11/4 11:05
 * @Version: 1.0
 */
@RestController
@RequestMapping("/callBack")
@Api(tags = "callback消息接口")
public class CallbackController {

    @Autowired
    private CallBackSender callBackSender;

    @ApiOperation("发送带有callback的消息，并验证")
    @GetMapping(value = "/test")
    public MsgStatus callback() {
        return callBackSender.send();
    }
}
