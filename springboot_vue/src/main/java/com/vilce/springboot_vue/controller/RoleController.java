package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.AdminPermission;
import com.vilce.springboot_vue.model.po.AdminRole;
import com.vilce.springboot_vue.service.AdminPermissionService;
import com.vilce.springboot_vue.service.AdminRoleMenuService;
import com.vilce.springboot_vue.service.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 角色相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.RoleController
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色相关API")
public class RoleController {
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;

    //    @GetMapping("/api/admin/role")
    @GetMapping("listAllRolesInfo")
    @ApiOperation(value = "列举所有角色权限菜单栏")
    public List<AdminRole> listAllRolesWithPermsAndMenus() {
        return adminRoleService.listAllRolesWithPermsAndMenus();
    }

    //    @PutMapping("/api/admin/role/status")
    @PutMapping("updateRoleStatus")
    @ApiOperation(value = "更改角色状态信息")
    public boolean updateRoleStatus(@RequestBody AdminRole role) {
        return adminRoleService.updateRoleStatus(role);
    }

    //    @PutMapping("/api/admin/role")
    @PutMapping("addOrUpdateRole")
    @ApiOperation(value = "添加角色或更新角色及角色权限信息")
    public boolean addOrUpdateRole(@RequestBody AdminRole role) {
        return adminRoleService.addOrUpdateRole(role);
    }

//    @GetMapping("/api/admin/role/perm")
    @GetMapping("getAllPermissions")
    @ApiOperation(value = "获取所有权限信息")
    public List<AdminPermission> listAllPerms() {
        return adminPermissionService.getAllPermissions();
    }

//    @PutMapping("/api/admin/role/menu")
    @PutMapping("updateRoleMenu")
    @ApiOperation(value = "更新角色菜单信息")
    public boolean updateRoleMenu(@RequestParam int rid, @RequestBody Map<String, List<Integer>> menusIds) {
        return  adminRoleMenuService.updateRoleMenu(rid, menusIds);
    }
}
