package com.vilce.springboot_vue.module.user.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @Description: 角色
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.po.AdminRole
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:36
 * @Version: 1.0
 */
@Data
@ApiModel(value = "角色")
public class AdminRole {

    @ApiModelProperty(value = "角色id",example = "0")
    private int id;
    @ApiModelProperty(value = "角色名",example = "test")
    private String name;
    @ApiModelProperty(value = "角色中文名",example = "示例")
    private String name_zh;
    @ApiModelProperty(value = "角色状态",example = "true")
    private boolean enabled;
    @ApiModelProperty(value = "角色权限",example = "{}")
    private List<AdminPermission> perms;
    @ApiModelProperty(value = "角色菜单",example = "{}")
    private List<AdminMenu> menus;
}
