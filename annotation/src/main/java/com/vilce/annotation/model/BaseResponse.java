package com.vilce.annotation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.model.BaseResponse
 * @Author: 雷才哲
 * @Date: 2019/11/13 15:23
 * @Version: 1.0
 */
@Data
public class BaseResponse implements Serializable {
    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
