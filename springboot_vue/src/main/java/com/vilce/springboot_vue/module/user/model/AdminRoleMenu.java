package com.vilce.springboot_vue.module.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 角色菜单栏
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.AdminRoleMenu
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:36
 * @Version: 1.0
 */
@Data
@ApiModel(value = "角色菜单")
public class AdminRoleMenu {

    @ApiModelProperty(value = "角色菜单id",example = "0")
    private int id;
    @ApiModelProperty(value = "角色rid",example = "0")
    private int rid;
    @ApiModelProperty(value = "菜单mid",example = "0")
    private int mid;
}
