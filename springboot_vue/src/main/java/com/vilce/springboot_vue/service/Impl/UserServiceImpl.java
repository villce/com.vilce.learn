package com.vilce.springboot_vue.service.Impl;

import com.vilce.springboot_vue.dao.UserMapper;
import com.vilce.springboot_vue.model.po.User;
import com.vilce.springboot_vue.model.vo.request.UserReq;
import com.vilce.springboot_vue.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.UserServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:39
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(UserReq req) {
        User user = userMapper.getUserByNamePassword(req);
        if (ObjectUtils.isNotEmpty(user)){
            return true;
        }
        return false;
    }
}
