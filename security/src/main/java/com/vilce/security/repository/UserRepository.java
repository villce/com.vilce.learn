package com.vilce.security.repository;

import com.vilce.security.model.UserDO;
import org.springframework.stereotype.Component;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.repository.UserRepository
 * @Author: 雷才哲
 * @Date: 2019/12/11 11:28
 * @Version: 1.0
 */
@Component
public interface UserRepository {

    void insert(UserDO userDO);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    UserDO findByUsername(String username);
}
