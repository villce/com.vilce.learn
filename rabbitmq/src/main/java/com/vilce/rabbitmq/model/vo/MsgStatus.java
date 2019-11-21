package com.vilce.rabbitmq.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.MsgStatus
 * @Author: 雷才哲
 * @Date: 2019/11/1 16:12
 * @Version: 1.0
 */
@Data
public class MsgStatus {
    @ApiModelProperty("消息发送状态")
    private String msgStatus;
}
