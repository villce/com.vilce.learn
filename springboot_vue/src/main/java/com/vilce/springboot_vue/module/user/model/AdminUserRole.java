package com.vilce.springboot_vue.module.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 用户角色
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.AdminUserRole
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:36
 * @Version: 1.0
 */
@Data
@ApiModel(value = "用户角色")
public class AdminUserRole {
    @ApiModelProperty(value = "用户角色id",example = "0")
    private int id;
    @ApiModelProperty(value = "用户uid",example = "0")
    private int uid;
    @ApiModelProperty(value = "角色rid",example = "0")
    private int rid;
}
