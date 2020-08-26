package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.po.AdminRole;
import com.vilce.springboot_vue.model.po.AdminUserRole;

import java.util.List;

/**
 * @Description: 用户角色相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.AdminUserRoleService
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:37
 * @Version: 1.0
 */
public interface AdminUserRoleService {

    /**
     * 更新用户角色信息
     *
     * @param id
     * @param roles
     * @return
     */
    boolean updateRoleChanges(int id, List<AdminRole> roles);

    /**
     * 根据用户id获取用户角色
     *
     * @param uid
     * @return
     */
    List<AdminUserRole> getUserRoleByUid(int uid);
}
