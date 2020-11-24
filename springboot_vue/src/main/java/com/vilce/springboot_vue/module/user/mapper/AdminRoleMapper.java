package com.vilce.springboot_vue.module.user.mapper;

import com.vilce.springboot_vue.module.user.model.po.AdminRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 角色相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.mapper.AdminRoleMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:15
 * @Version: 1.0
 */
@Mapper
public interface AdminRoleMapper {

    /**
     * 根据id获取角色
     *
     * @param id
     * @return
     */
    AdminRole getRoleById(int id);


    /**
     * 获取所有角色
     *
     * @return
     */
    List<AdminRole> getAllRole();

    /**
     * 更新角色状态信息
     *
     * @param role
     * @return
     */
    boolean updateRoleStatus(AdminRole role);

    /**
     * 添加角色信息
     *
     * @param role
     * @return
     */
    boolean addRole(AdminRole role);

    /**
     * 根据角色信息
     * @param role
     * @return
     */
    boolean updateRole(AdminRole role);
}
