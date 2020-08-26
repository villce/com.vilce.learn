package com.vilce.springboot_vue.service.Impl;

import com.vilce.springboot_vue.mapper.AdminPermissionMapper;
import com.vilce.springboot_vue.mapper.AdminRolePermissionMapper;
import com.vilce.springboot_vue.model.po.*;
import com.vilce.springboot_vue.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 权限相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.AdminPermissionServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/25 17:07
 * @Version: 1.0
 */
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;
    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;
    @Autowired
    UserService userService;

    /**
     * 获取所有权限信息
     *
     * @return
     */
    @Override
    public List<AdminPermission> getAllPermissions() {
        return adminPermissionMapper.getAllPermissions();
    }

    /**
     * 确定客户端在请求特定API时是否需要许可。
     *
     * @param requestAPI API requested by client
     */
    @Override
    public boolean needFilter(String requestAPI) {
        List<AdminPermission> ps = adminPermissionMapper.getAllPermissions();
        for (AdminPermission p : ps) {
            // match prefix
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据角色id获取角色权限
     *
     * @param rid
     * @return
     */
    @Override
    public List<AdminPermission> listPermsByRoleId(int rid) {
        // 获取角色权限id
        List<AdminRolePermission> rolePermissionList = adminRolePermissionService.getRolePermissionByRid(rid);
        // 对应权限id获取权限信息
        List<AdminPermission> permissionList = new LinkedList<>();
        rolePermissionList.forEach(rolePermission -> {
            AdminPermission permission = adminPermissionMapper.getPermissionById(rolePermission.getPid());
            permissionList.add(permission);
        });
        return permissionList;
    }

    /**
     * 根据用户名获取权限
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> listPermissionURLsByUser(String username) {
        // 根据用户名获取用户信息
        User user = userService.getUserByUsername(username);
        // 根据用户id获取用户角色
        List<AdminUserRole> userRoleList = adminUserRoleService.getUserRoleByUid(user.getId());
        List<AdminPermission> perms = new LinkedList<>();
        userRoleList.forEach(userRole -> {
            // 根据用户角色id获取角色权限
            List<AdminRolePermission> rolePermissionList = adminRolePermissionMapper.getRolePermissionByRid(userRole.getRid());
            rolePermissionList.forEach(rolePermission -> {
                // 根据权限id获取权限信息
               perms.add(adminPermissionMapper.getPermissionById(rolePermission.getPid()));
            });
        });
        // 去重
        Set<String> URLs = perms.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());
        return URLs;
    }
}
