package com.vilce.springboot_vue.module.user.service;

import com.vilce.springboot_vue.module.user.model.AdminMenu;

import java.util.List;

/**
 * @Description: 菜单相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.service.AdminMenuService
 * @Author: 雷才哲
 * @Date: 2020/8/25 14:04
 * @Version: 1.0
 */
public interface AdminMenuService {

    /**
     * 根据角色id获取菜单栏
     * @param rid
     * @return
     */
    List<AdminMenu> getMenusByRoleId(int rid);

    /**
     * 获取当前用户下的菜单栏
     *
     * @return
     */
    List<AdminMenu> getMenusByCurrentUser();
}
