package com.vilce.springboot_vue.module.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.vo.UserReq
 * @Author: 雷才哲
 * @Date: 2020/11/24 15:41
 * @Version: 1.0
 */
@Data
@ApiModel(value = "用户入参")
public class UserReq {
    @ApiModelProperty(value = "用户id",example = "0")
    private int id;
    @ApiModelProperty(value = "用户名",example = "示例")
    private String username;
    @ApiModelProperty(value = "用户密码",example = "123")
    private String password;
    @ApiModelProperty(value = "加密盐度",example = "abc")
    private String salt;
    @ApiModelProperty(value = "用户头像",example = "0.jpg")
    private String icon;
    @ApiModelProperty(value = "用户状态",example = "true")
    private boolean enabled;
}
