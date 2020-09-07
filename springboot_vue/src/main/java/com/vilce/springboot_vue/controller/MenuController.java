package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.AdminMenu;
import com.vilce.springboot_vue.service.AdminMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 菜单相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.MenuController
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单相关API")
public class MenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    @GetMapping("getUserMenu")
    @ApiOperation(value = "获取当前用户下的菜单栏")
    public List<AdminMenu> getMenusByCurrentUser() {
        return adminMenuService.getMenusByCurrentUser();
    }

    @GetMapping("get1RoleMenu")
    @ApiOperation(value = "获取角色id为1时的菜单栏")
    public List<AdminMenu> get1RoleMenu() {
        return adminMenuService.getMenusByRoleId(1);
    }
}
