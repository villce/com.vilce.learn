package com.vilce.springboot_vue.module.user.service;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.user.model.AdminPermission;
import com.vilce.springboot_vue.module.user.model.AdminRolePermission;

import java.util.List;

/**
 * @Description: 角色权限相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.service.AdminRolePermissionService
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
    BaseResponse updateRolePermission(int rid, List<AdminPermission> perms);

    /**
     * 根据角色id获取角色权限
     *
     * @param rid
     * @return
     */
    List<AdminRolePermission> getRolePermissionByRid(int rid);
}
