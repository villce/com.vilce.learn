package com.vilce.springboot_vue.service.Impl;

import com.vilce.springboot_vue.mapper.AdminRoleMenuMapper;
import com.vilce.springboot_vue.model.po.AdminRoleMenu;
import com.vilce.springboot_vue.model.po.AdminUserRole;
import com.vilce.springboot_vue.service.AdminRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 角色菜单相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.AdminRoleMenuServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/25 17:02
 * @Version: 1.0
 */
@Service
public class AdminRoleMenuServiceImpl implements AdminRoleMenuService {
    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;

    /**
     * 根据用户角色获取角色菜单
     *
     * @param userRoleList
     * @return
     */
    @Override
    public List<AdminRoleMenu> getRoleMenuByUserRole(List<AdminUserRole> userRoleList) {
        // todo 代码优化
        List<AdminRoleMenu> adminRoleMenuList = new LinkedList<>();
        userRoleList.forEach(userRole -> {
            List<AdminRoleMenu> adminRoleMenus = adminRoleMenuMapper.getRoleMenuAByRid(userRole.getRid());
            adminRoleMenuList.addAll(adminRoleMenus);
        });
        return adminRoleMenuList;
    }

    /**
     * 根据角色id获取角色菜单
     *
     * @param rid
     * @return
     */
    @Override
    public List<AdminRoleMenu> getRoleMenuByRoleId(int rid) {
        return adminRoleMenuMapper.getRoleMenuAByRid(rid);
    }

    /**
     * 更新角色菜单信息
     *
     * @param rid
     * @param menusIds
     */
    @Override
    public boolean updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        // 删除之前角色菜单信息
        if (adminRoleMenuMapper.deleteRoleMenuByRid(rid)){
            for (Integer mid : menusIds.get("menusIds")) {
                if (!adminRoleMenuMapper.addRoleMenu(rid, mid)) {
                    // todo 返回message "保存失败，未知错误"
                }
            }
        }else {
            // todo 返回message "删除失败，未知错误"
        }
        return true;
    }
}
