package com.vilce.springboot_vue.module.user.service.impl;

import com.vilce.springboot_vue.module.user.mapper.AdminMenuMapper;
import com.vilce.springboot_vue.module.user.model.AdminMenu;
import com.vilce.springboot_vue.module.user.model.AdminRoleMenu;
import com.vilce.springboot_vue.module.user.model.AdminUserRole;
import com.vilce.springboot_vue.module.user.model.User;
import com.vilce.springboot_vue.module.user.service.AdminMenuService;
import com.vilce.springboot_vue.module.user.service.AdminRoleMenuService;
import com.vilce.springboot_vue.module.user.service.AdminUserRoleService;
import com.vilce.springboot_vue.module.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 菜单相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.service.impl.AdminMenuServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/25 14:05
 * @Version: 1.0
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    /**
     * 根据父id获取所有子菜单
     *
     * @param parentId
     * @return
     */
    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuMapper.findAllByParentId(parentId);
    }

    /**
     * 获取当前用户下的菜单栏
     *
     * @return
     */
    @Override
    public List<AdminMenu> getMenusByCurrentUser() {
        // 获取当前用户
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getUserByUsername(username);

        // 获取用户角色
        List<AdminUserRole> userRoleList = adminUserRoleService.getUserRoleByUid(user.getId());

        // 获取角色菜单
        List<AdminRoleMenu> roleMenuList = adminRoleMenuService.getRoleMenuByUserRole(userRoleList);

        List<AdminMenu> menuList = new LinkedList<>();
        roleMenuList.forEach(roleMenu -> {
            AdminMenu adminMenu = adminMenuMapper.getMenuById(roleMenu.getMid());
            menuList.add(adminMenu);
        });
        menuList.stream().distinct().collect(Collectors.toList());
        // 调整菜单结构
        handleMenus(menuList);
        return menuList;
    }

    /**
     * 根据角色id获取菜单
     * @param rid
     * @return
     */
    @Override
    public List<AdminMenu> getMenusByRoleId(int rid) {
        // 获取角色菜单
        List<AdminRoleMenu> roleMenuList = adminRoleMenuService.getRoleMenuByRoleId(rid);
        // 对应菜单id获取菜单信息
        List<AdminMenu> menuList = new LinkedList<>();
        roleMenuList.forEach(roleMenu -> {
            AdminMenu adminMenu = adminMenuMapper.getMenuById(roleMenu.getMid());
            menuList.add(adminMenu);
        });
        handleMenus(menuList);
        return menuList;
    }

    /**
     * 调整菜单结构
     *
     * @param menuList 菜单项列表无结构
     */
    public void handleMenus(List<AdminMenu> menuList) {
        menuList.forEach(m -> {
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });
        menuList.removeIf(m -> m.getParent_id() != 0);
    }
}
