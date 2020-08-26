package com.vilce.springboot_vue.mapper;

import com.vilce.springboot_vue.model.po.AdminRolePermission;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 角色权限相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.mapper.AdminRolePermissionMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:23
 * @Version: 1.0
 */
@Component
public interface AdminRolePermissionMapper {

    /**
     * 根据角色id获取角色权限
     *
     * @param rid
     * @return
     */
    List<AdminRolePermission> getRolePermissionByRid(int rid);

    /**
     * 根据角色id删除角色权限
     *
     * @param rid
     */
    boolean deleteRolePermissionByRid(int rid);

    /**
     * 保存角色权限信息
     *
     * @param rid
     * @param pid
     * @return
     */
    boolean addRolePermission(int rid, int pid);
}
