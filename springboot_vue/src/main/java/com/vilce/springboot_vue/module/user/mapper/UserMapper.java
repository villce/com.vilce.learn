package com.vilce.springboot_vue.module.user.mapper;

import com.vilce.springboot_vue.module.user.model.User;
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
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 获取所有的用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 更新用户基础信息
     *
     * @param user
     * @return
     */
    boolean updateUserInfo(User user);

    /**
     * 更新用户状态信息
     *
     * @param user
     * @return
     */
    boolean updateUserStatus(User user);

    /**
     * 更新用户密码信息
     *
     * @param requestUser
     * @return
     */
    boolean updatePassword(User requestUser);
}
