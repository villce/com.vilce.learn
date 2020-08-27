package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.utils.JSONUtils;
import com.vilce.springboot_vue.mapper.AdminMenuMapper;
import com.vilce.springboot_vue.model.po.AdminMenu;
import com.vilce.springboot_vue.model.po.AdminRoleMenu;
import com.vilce.springboot_vue.model.po.AdminUserRole;
import com.vilce.springboot_vue.model.po.User;
import com.vilce.springboot_vue.service.AdminMenuService;
import com.vilce.springboot_vue.service.AdminRoleMenuService;
import com.vilce.springboot_vue.service.AdminUserRoleService;
import com.vilce.springboot_vue.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 菜单相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.AdminMenuServiceImpl
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
        System.out.println(username);
        User user = userService.getUserByUsername(username);

        // 获取用户角色
        List<AdminUserRole> userRoleList = adminUserRoleService.getUserRoleByUid(user.getId());
        System.out.println(JSONUtils.toJsonPretty(userRoleList));

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
