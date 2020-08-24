package com.vilce.knife4j.service;

import com.vilce.knife4j.model.User;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.security.service.TestService
 * @Author: 雷才哲
 * @Date: 2020/6/22 13:34
 * @Version: 1.0
 */
public interface TestService {
    User getUser();

    User findUser(User user);
}
