package com.vilce.rabbitmq.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.Msg
 * @Author: 雷才哲
 * @Date: 2019/10/31 9:21
 * @Version: 1.0
 */
@ApiModel(value = "消息")
public class Msg implements Serializable {
    @ApiModelProperty(value = "消息内容", name = "content", required = true)
    private String content;
    @ApiModelProperty(value = "消息ID", name = "id", required = true)
    @Min(0)
    @NotNull(message = "id不能为空")
    private Integer id;
    @ApiModelProperty(value = "延迟时间", name = "ttl", required = true)
    @Min(0L)
    @NotNull(message = "ttl不能为空")
    private Long ttl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
