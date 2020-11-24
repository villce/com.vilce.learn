package com.vilce.springboot_vue.module.user.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 权限
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.po.AdminPermission
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:36
 * @Version: 1.0
 */
@Data
@ApiModel(value = "权限")
public class AdminPermission {

    @ApiModelProperty(value = "权限id",example = "0")
    private int id;
    @ApiModelProperty(value = "权限名",example = "示例")
    private String name;
    @ApiModelProperty(value = "权限描述",example = "示例")
    private String desc_;
    @ApiModelProperty(value = "触发权限检查路径",example = "/user/delete")
    private String url;
}
