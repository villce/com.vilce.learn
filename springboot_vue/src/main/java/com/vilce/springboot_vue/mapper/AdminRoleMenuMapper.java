package com.vilce.springboot_vue.mapper;

import com.vilce.springboot_vue.model.po.AdminRoleMenu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 角色菜单相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.mapper.AdminRoleMenuMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:17
 * @Version: 1.0
 */
@Component
public interface AdminRoleMenuMapper {

    /**
     * 根据角色id获取菜单栏
     *
     * @param rid
     * @return
     */
    List<AdminRoleMenu> getRoleMenuAByRid(int rid);

    /**
     * 根据角色id删除所有菜单
     *
     * @param rid
     */
    boolean deleteRoleMenuByRid(int rid);

    /**
     * 保存角色菜单
     *
     * @param rid
     * @param mid
     * @return
     */
    boolean addRoleMenu(int rid, int mid);
}
