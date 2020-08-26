package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.po.AdminRoleMenu;
import com.vilce.springboot_vue.model.po.AdminUserRole;

import java.util.List;
import java.util.Map;

/**
 * @Description: 角色菜单相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.AdminRoleMenuService
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:37
 * @Version: 1.0
 */
public interface AdminRoleMenuService {

    /**
     * 根据用户角色获取角色菜单
     *
     * @param userRoleList
     * @return
     */
    List<AdminRoleMenu> getRoleMenuByUserRole(List<AdminUserRole> userRoleList);

    /**
     * 根据角色id获取菜单
     *
     * @param rid
     * @return
     */
    List<AdminRoleMenu> getRoleMenuByRoleId(int rid);

    /**
     * 更新角色菜单信息
     *
     * @param rid
     * @param menusIds
     * @return
     */
    boolean updateRoleMenu(int rid, Map<String, List<Integer>> menusIds);

}
