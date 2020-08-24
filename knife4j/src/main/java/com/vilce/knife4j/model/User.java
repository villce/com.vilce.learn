package com.vilce.knife4j.model;

import com.vilce.common.model.po.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.security.model.User
 * @Author: 雷才哲
 * @Date: 2020/6/22 13:34
 * @Version: 1.0
 */
@Data
@ApiModel(value = "用户")
public class User extends BaseRequest {

    public User() {
        super.baseInfo = null;
    }

    @ApiModelProperty(value = "名字", example = "张三")
    private String name;
    @ApiModelProperty(value = "年龄", example = "3")
    private Integer age;
}
