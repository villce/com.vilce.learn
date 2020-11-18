package com.vilce.springboot_vue.module.user.mapper;

import com.vilce.springboot_vue.module.user.model.AdminPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 权限相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.mapper.AdminPermissionMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:13
 * @Version: 1.0
 */
@Mapper
public interface AdminPermissionMapper {

    /**
     * 根据id获取权限
     *
     * @param id
     * @return
     */
    AdminPermission getPermissionById(int id);

    /**
     * 获取所有权限信息
     *
     * @return
     */
    List<AdminPermission> getAllPermissions();

}
