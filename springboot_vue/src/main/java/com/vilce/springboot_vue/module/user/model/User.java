package com.vilce.springboot_vue.module.user.model;

import com.vilce.springboot_vue.module.user.model.AdminRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Description: 用户
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.User
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:36
 * @Version: 1.0
 */
@Data
@ApiModel(value = "用户")
public class User {

    @ApiModelProperty(value = "用户id",example = "0")
    private int id;
    @ApiModelProperty(value = "用户名",example = "示例")
    private String username;
    @ApiModelProperty(value = "用户密码",example = "123")
    private String password;
    @ApiModelProperty(value = "加密盐度",example = "abc")
    private String salt;
    @ApiModelProperty(value = "真实姓名",example = "示例")
    private String name;
    @ApiModelProperty(value = "手机号",example = "12345678901")
    @Pattern(regexp = "^[0-9]+$", message = "手机号码错误")
    private String phone;
    @ApiModelProperty(value = "邮箱",example = "123@qq.com")
    private String email;
    @ApiModelProperty(value = "用户状态",example = "true")
    private boolean enabled;
    @ApiModelProperty(value = "用户角色",example = "[]")
    private List<AdminRole> roles;
}
