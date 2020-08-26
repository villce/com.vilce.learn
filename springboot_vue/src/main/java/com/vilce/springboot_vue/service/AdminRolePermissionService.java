package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.po.AdminPermission;
import com.vilce.springboot_vue.model.po.AdminRolePermission;

import java.util.List;

/**
 * @Description: 角色权限相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.AdminRolePermissionService
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:37
 * @Version: 1.0
 */
public interface AdminRolePermissionService {

    /**
     * 更新角色的权限信息
     *
     * @param rid
     * @param perms
     * @return
     */
    boolean updateRolePermission(int rid, List<AdminPermission> perms);

    /**
     * 根据角色id获取角色权限
     *
     * @param rid
     * @return
     */
    List<AdminRolePermission> getRolePermissionByRid(int rid);
}
