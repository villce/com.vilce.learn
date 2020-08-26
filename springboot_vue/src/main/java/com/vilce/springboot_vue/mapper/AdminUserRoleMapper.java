package com.vilce.springboot_vue.mapper;

import com.vilce.springboot_vue.model.po.AdminUserRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 用户角色相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.mapper.AdminUserRoleMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:25
 * @Version: 1.0
 */
@Component
public interface AdminUserRoleMapper {

    /**
     * 根据用户id获取用户角色
     *
     * @param uid
     * @return
     */
    List<AdminUserRole> getUserRoleByUid(int uid);

    /**
     * 根据用户id删除用户角色
     *
     * @param uid
     */
    boolean deleteUserRoleByUid(int uid);

    /**
     * 添加用户角色
     *
     * @param uid
     * @param rid
     */
    boolean addUserRole(int uid, int rid);
}
