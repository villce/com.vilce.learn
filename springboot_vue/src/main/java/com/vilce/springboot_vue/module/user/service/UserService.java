package com.vilce.springboot_vue.module.user.service;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.user.model.po.AdminUser;
import com.vilce.springboot_vue.module.user.model.vo.UserReq;

import java.util.List;

/**
 * @Description: 用户相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.service.UserService
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:39
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 列出所有用户信息
     *
     * @return
     */
    List<AdminUser> listAllUsers();

    /**
     * 获取指定用户信息
     *
     * @param userId
     * @return
     */
    AdminUser findUser(int userId);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    AdminUser getUserByUsername(String username);

    /**
     * 更新用户状态信息
     *
     * @param requestUser
     * @return
     */
    BaseResponse updateUserStatus(AdminUser requestUser);

    /**
     * 更新用户密码
     *
     * @param requestUser
     * @return
     */
    BaseResponse updatePassword(AdminUser requestUser);

    /**
     * 更新用户基础信息
     *
     * @param requestUser
     * @return
     */
    BaseResponse editUser(AdminUser requestUser);

    /**
     * 添加用户
     *
     * @param userReq
     * @return
     */
    BaseResponse addUser(UserReq userReq);

    /**
     * 获取当前用户信息
     *
     * @return
     */
    AdminUser currentUser();
}
