package com.vilce.springboot_vue.module.user.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description: 菜单栏
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.po.AdminMenu
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:36
 * @Version: 1.0
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_zh() {
        return name_zh;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public String getIcon_cls() {
        return icon_cls;
    }

    public void setIcon_cls(String icon_cls) {
        this.icon_cls = icon_cls;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<AdminMenu> getChildren() {
        return children;
    }

    public void setChildren(List<AdminMenu> children) {
        this.children = children;
    }
}
