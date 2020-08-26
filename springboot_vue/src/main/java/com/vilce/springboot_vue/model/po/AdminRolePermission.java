package com.vilce.springboot_vue.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 角色权限
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.po.AdminRolePermission
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:36
 * @Version: 1.0
 */
@Data
@ApiModel(value = "角色权限")
public class AdminRolePermission {
    @ApiModelProperty(value = "角色权限id",example = "0")
    private int id;
    @ApiModelProperty(value = "角色rid",example = "0")
    private int rid;
    @ApiModelProperty(value = "权限pid",example = "0")
    private int pid;
}
