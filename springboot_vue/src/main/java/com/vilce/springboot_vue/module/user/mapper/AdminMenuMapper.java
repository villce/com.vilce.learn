package com.vilce.springboot_vue.module.user.mapper;

import com.vilce.springboot_vue.module.user.model.AdminMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 菜单相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.mapper.AdminPermissionMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 14:10
 * @Version: 1.0
 */
@Mapper
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
     * @param mid
     * @return
     */
    AdminMenu getMenuById(int mid);
}
