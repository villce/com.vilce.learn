package com.vilce.springboot_vue.model.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description: 菜单栏
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.po.AdminMenu
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:36
 * @Version: 1.0
 */
@Data
@ApiModel(value = "菜单")
public class AdminMenu {

    @ApiModelProperty(value = "菜单id",example = "0")
    private int id;
    @ApiModelProperty(value = "菜单路径",example = "/admin")
    private String path;
    @ApiModelProperty(value = "菜单名",example = "test")
    private String name;
    @ApiModelProperty(value = "菜单中文名",example = "示例")
    private String name_zh;
    @ApiModelProperty(value = "菜单图标",example = "d:/img/1.png")
    private String icon_cls;
    @ApiModelProperty(value = "菜单对应前端组件名称",example = "示例")
    private String component;
    @ApiModelProperty(value = "菜单父pid",example = "0")
    private int parent_id;
    @ApiModelProperty(value = "子菜单",example = "{}")
    private List<AdminMenu> children;
}
