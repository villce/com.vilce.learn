package com.vilce.springboot_vue.module.user.mapper;

import com.vilce.springboot_vue.module.user.model.po.AdminUser;
import com.vilce.springboot_vue.module.user.model.vo.UserReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 用户相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.mapper.UserMapper
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:39
 * @Version: 1.0
 */
@Mapper
public interface UserMapper {

    /**
     * 添加用户
     *
     * @param userReq
     * @return
     */
    boolean addUser(UserReq userReq);

    /**
     * 获取所有的用户
     *
     * @return
     */
    List<AdminUser> findAll();

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    AdminUser getUserByUsername(String username);

    /**
     * 根据ID获取用户
     *
     * @param userId 用户ID
     * @return
     */
    AdminUser getUserById(int userId);

    /**
     * 更新用户基础信息
     *
     * @param user
     * @return
     */
    boolean updateUserInfo(AdminUser user);

    /**
     * 更新用户状态信息
     *
     * @param user
     * @return
     */
    boolean updateUserStatus(AdminUser user);

    /**
     * 更新用户密码信息
     *
     * @param requestUser
     * @return
     */
    boolean updatePassword(AdminUser requestUser);
}
