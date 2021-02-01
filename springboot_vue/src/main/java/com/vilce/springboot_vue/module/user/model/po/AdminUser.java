package com.vilce.springboot_vue.module.user.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description: 用户
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.model.po.AdminUser
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:36
 * @Version: 1.0
 */
@ApiModel(value = "用户")
public class AdminUser {
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
    @ApiModelProperty(value = "用户角色",example = "[]")
    private List<AdminRole> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }
}
