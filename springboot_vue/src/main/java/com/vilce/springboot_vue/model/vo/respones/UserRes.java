package com.vilce.springboot_vue.model.vo.respones;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.vo.respones.UserRes
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:37
 * @Version: 1.0
 */
@Data
@ApiModel("用户返参")
public class UserRes {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String password;
}
