package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.model.exception.BasicException;
import com.vilce.springboot_vue.mapper.AdminUserRoleMapper;
import com.vilce.springboot_vue.model.po.AdminRole;
import com.vilce.springboot_vue.model.po.AdminUserRole;
import com.vilce.springboot_vue.service.AdminUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean updateRoleChanges(int uid, List<AdminRole> roles) {
        // 删除旧的用户角色信息
        if (adminUserRoleMapper.deleteUserRoleByUid(uid)) {
            roles.forEach(r -> {
                if (!adminUserRoleMapper.addUserRole(uid, r.getId())){
                    // todo 返回message "用户角色添加失败"
                    throw new BasicException(-1, "用户角色添加失败");
                }
            });
            return true;
        }else {
            // todo 返回message "用户角色信息删除失败"
            return false;
        }
    }
}
