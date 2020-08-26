package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.po.AdminPermission;

import java.util.List;
import java.util.Set;

/**
 * @Description: 权限相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.AdminPermissionService
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:37
 * @Version: 1.0
 */
public interface AdminPermissionService {

    /**
     * 根据角色id获取角色权限
     *
     * @param id
     * @return
     */
    List<AdminPermission> listPermsByRoleId(int id);

    /**
     * 获取用户相关权限URL
     *
     * @param username
     * @return
     */
    Set<String> listPermissionURLsByUser(String username);

    /**
     * 判断是否需要过滤
     *
     * @param requestAPI
     * @return
     */
    boolean needFilter(String requestAPI);

    /**
     * 获取所有权限信息
     *
     * @return
     */
    List<AdminPermission> getAllPermissions();
}
