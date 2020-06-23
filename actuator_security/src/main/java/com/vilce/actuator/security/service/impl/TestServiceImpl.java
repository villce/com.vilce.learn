package com.vilce.actuator.security.service.impl;

import com.vilce.actuator.security.model.User;
import com.vilce.actuator.security.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.security.service.impl.TestServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/6/22 13:35
 * @Version: 1.0
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public User getUser() {
        User user = new User();
        user.setName("vilce");
        user.setAge(3);
        return user;
    }
}
