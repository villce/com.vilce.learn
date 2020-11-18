package com.vilce.springboot_vue.module.user.service;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.user.model.AdminRole;
import com.vilce.springboot_vue.module.user.model.AdminUserRole;

import java.util.List;

/**
 * @Description: 用户角色相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.service.AdminUserRoleService
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:37
 * @Version: 1.0
 */
public interface AdminUserRoleService {

    /**
     * 更新用户角色信息
     *
     * @param uid
     * @param roles
     * @return
     */
    BaseResponse updateRoleChanges(int uid, List<AdminRole> roles);

    /**
     * 根据用户id获取用户角色
     *
     * @param uid
     * @return
     */
    List<AdminUserRole> getUserRoleByUid(int uid);
}
