package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.mapper.AdminUserRoleMapper;
import com.vilce.springboot_vue.model.po.AdminRole;
import com.vilce.springboot_vue.model.po.AdminUserRole;
import com.vilce.springboot_vue.service.AdminUserRoleService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 用户角色相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.AdminUserRoleServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/25 16:46
 * @Version: 1.0
 */
@Service
public class AdminUserRoleServiceImpl implements AdminUserRoleService {

    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;

    /**
     * 根据用户id获取用户角色
     *
     * @param uid
     * @return
     */
    @Override
    public List<AdminUserRole> getUserRoleByUid(int uid) {
        return adminUserRoleMapper.getUserRoleByUid(uid);
    }

    /**
     * 更新用户角色信息
     *
     * @param uid
     * @param roles
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse updateRoleChanges(int uid, List<AdminRole> roles) {
        List<AdminUserRole> userRoleList = adminUserRoleMapper.getUserRoleByUid(uid);
        if (ObjectUtils.isNotEmpty(userRoleList)) {
            // 删除旧的用户角色信息
            if (!adminUserRoleMapper.deleteUserRoleByUid(uid)) {
                return BaseResponse.buildResponse(-1, "删除用户角色信息失败！");
            }
        }
        for (AdminRole role : roles) {
            if (!adminUserRoleMapper.addUserRole(uid, role.getId())) {
                return BaseResponse.buildResponse(-1, "添加用户角色信息失败！");
            }
        }
        return BaseResponse.buildResponse(0, "更新用户角色信息成功！");
    }
}
