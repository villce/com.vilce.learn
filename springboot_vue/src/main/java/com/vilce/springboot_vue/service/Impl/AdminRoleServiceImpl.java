package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.mapper.AdminRoleMapper;
import com.vilce.springboot_vue.model.po.*;
import com.vilce.springboot_vue.service.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 角色相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.AdminRoleServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:51
 * @Version: 1.0
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;
    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 获取所有角色权限菜单栏
     *
     * @return
     */
    @Override
    public List<AdminRole> listAllRolesWithPermsAndMenus() {
        // 获取所有的角色
        List<AdminRole> roleList = adminRoleMapper.getAllRole();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for (AdminRole role : roleList) {
            // 获取角色对应的角色权限
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            // 获取角色对应的角色菜单
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roleList;
    }

    /**
     * 根据用户名获取用户所有角色
     *
     * @param uid
     * @return
     */
    @Override
    public List<AdminRole> getRolesByUserId(int uid) {
        List<AdminRole> roleList = new LinkedList<>();
        // 获取用户角色id
        List<AdminUserRole> userRoleList = adminUserRoleService.getUserRoleByUid(uid);
        // 根据用户角色id获取角色信息
        userRoleList.forEach(userRole -> {
            roleList.add(adminRoleMapper.getRoleById(userRole.getRid()));
        });
        return roleList;
    }

    /**
     * 更新角色状态信息
     *
     * @param role
     * @return
     */
    @Override
    public BaseResponse updateRoleStatus(AdminRole role) {
        if (adminRoleMapper.updateRoleStatus(role)) {
            return BaseResponse.buildResponse(0, "状态更新成功！");
        }else {
            throw new BasicException(ResultStatus.FAIL.getStatus(), "状态更新失败!");
        }
    }

    /**
     * 添加或更新角色信息
     *
     * @param role
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse addOrUpdateRole(@RequestBody AdminRole role) {
        if (ObjectUtils.isEmpty(role.getId())) {
            // 当角色id为空时，添加角色
            if (adminRoleMapper.addRole(role)) {
                return BaseResponse.buildResponse(0, "添加角色成功！");
            }else {
                throw new BasicException(ResultStatus.FAIL.getStatus(), "添加角色失败!");
            }
        }else {
            // 当角色id不为空时，更新角色，更新角色对应权限信息
            if (adminRoleMapper.updateRole(role)) {
                BaseResponse response = adminRolePermissionService.updateRolePermission(role.getId(), role.getPerms());
                if (response.getStatus() == 0) {
                    return BaseResponse.buildResponse(0, "更新角色信息成功！");
                }else {
                    throw new BasicException(ResultStatus.FAIL.getStatus(), "更新角色信息失败!");
                }
            }else {
                throw new BasicException(ResultStatus.FAIL.getStatus(), "更新角色基础信息失败!");
            }
        }
    }
}
