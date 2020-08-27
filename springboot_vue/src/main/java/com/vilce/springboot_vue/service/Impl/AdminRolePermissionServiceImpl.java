package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.mapper.AdminRolePermissionMapper;
import com.vilce.springboot_vue.model.po.AdminPermission;
import com.vilce.springboot_vue.model.po.AdminRolePermission;
import com.vilce.springboot_vue.service.AdminRolePermissionService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 角色权限相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.AdminRolePermissionServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/25 17:00
 * @Version: 1.0
 */
@Service
public class AdminRolePermissionServiceImpl implements AdminRolePermissionService {

    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    /**
     * 根据角色id获取角色权限
     *
     * @param rid
     * @return
     */
    @Override
    public List<AdminRolePermission> getRolePermissionByRid(int rid) {
        return adminRolePermissionMapper.getRolePermissionByRid(rid);
    }

    /**
     * 保存权限更新信息
     *
     * @param rid
     * @param perms
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse updateRolePermission(int rid, List<AdminPermission> perms) {
        List<AdminRolePermission> rolePermissionList = adminRolePermissionMapper.getRolePermissionByRid(rid);
        if (ObjectUtils.isNotEmpty(rolePermissionList)) {
            if (!adminRolePermissionMapper.deleteRolePermissionByRid(rid)) {
                return BaseResponse.buildResponse(-1, "删除角色权限信息失败！");
            }
        }
        // 保存需要修改更新的角色权限信息
        for (AdminPermission permission : perms) {
            if (!adminRolePermissionMapper.addRolePermission(rid, permission.getId())) {
                return BaseResponse.buildResponse(-1, "添加角色权限信息失败！");
            }
        }
        return BaseResponse.buildResponse(0, "更新角色权限信息成功！");
    }

}
