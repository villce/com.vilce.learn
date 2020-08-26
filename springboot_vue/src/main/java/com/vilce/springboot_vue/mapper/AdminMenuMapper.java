package com.vilce.springboot_vue.mapper;

import com.vilce.springboot_vue.model.po.AdminMenu;
import com.vilce.springboot_vue.model.po.AdminRoleMenu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 菜单相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.mapper.AdminPermissionMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 14:10
 * @Version: 1.0
 */
@Component
public interface AdminMenuMapper {

    /**
     * 查询父类id下所有菜单
     *
     * @param parentId
     * @return
     */
    List<AdminMenu> findAllByParentId(int parentId);

    /**
     * 根据菜单id获取菜单信息
     *
     * @param roleMenu
     * @return
     */
    AdminMenu getMenuById(AdminRoleMenu roleMenu);
}
