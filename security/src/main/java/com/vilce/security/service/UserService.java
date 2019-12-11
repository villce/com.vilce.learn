package com.vilce.security.service;

import com.vilce.security.model.UserDO;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.service.UserService
 * @Author: 雷才哲
 * @Date: 2019/12/11 11:23
 * @Version: 1.0
 */
public interface UserService {
    /**
     * 添加新用户
     *
     * username 唯一， 默认 USER 权限
     */
    void insert(UserDO userDO);

    /**
     * 查询用户信息
     * @param username 账号
     * @return UserEntity
     */
    UserDO getByUsername(String username);
}
