package com.vilce.springboot_vue.service;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.model.po.AdminRole;

import java.util.List;

/**
 * @Description: 角色相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.AdminRoleService
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:37
 * @Version: 1.0
 */
public interface AdminRoleService {

    /**
     * 列出用户所有角色
     *
     * @param uid
     * @return
     */
    List<AdminRole> getRolesByUserId(int uid);

    /**
     * 获取所有角色权限菜单栏
     *
     * @return
     */
    List<AdminRole> listAllRolesWithPermsAndMenus();

    /**
     * 更改角色状态信息
     *
     * @param role
     * @return
     */
    BaseResponse updateRoleStatus(AdminRole role);

    /**
     * 添加或更新角色信息
     *
     * @param role
     * @return
     */
    BaseResponse addOrUpdateRole(AdminRole role);
}
