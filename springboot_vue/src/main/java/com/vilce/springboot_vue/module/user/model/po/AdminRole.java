package com.vilce.springboot_vue.module.user.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description: 角色
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.po.AdminRole
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:36
 * @Version: 1.0
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AdminPermission> getPerms() {
        return perms;
    }

    public void setPerms(List<AdminPermission> perms) {
        this.perms = perms;
    }

    public List<AdminMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<AdminMenu> menus) {
        this.menus = menus;
    }
}
