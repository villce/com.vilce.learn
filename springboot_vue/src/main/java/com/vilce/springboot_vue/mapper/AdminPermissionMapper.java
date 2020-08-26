package com.vilce.springboot_vue.mapper;

import com.vilce.springboot_vue.model.po.AdminPermission;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 权限相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.mapper.AdminPermissionMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:13
 * @Version: 1.0
 */
@Component
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
