package com.vilce.swagger.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.Task
 * @Author: 雷才哲
 * @Date: 2019/11/11 11:06
 * @Version: 1.0
 */
@Data
@ApiModel("任务")
public class Task {
    @ApiModelProperty(value = "任务执行结果")
    private boolean result;
}
