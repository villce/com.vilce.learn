package com.vilce.knife4j.service.impl;

import com.vilce.knife4j.model.User;
import com.vilce.knife4j.service.TestService;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public User findUser(User user) {
        String name = StringUtils.join(user.getName(), "找到了！");
        Integer age = user.getAge() + 1;
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
