package com.vilce.test.service;

import com.vilce.test.model.po.User;
import com.vilce.test.model.vo.UserRes;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.service.AsyncService
 * @Author: 雷才哲
 * @Date: 2019/12/6 16:24
 * @Version: 1.0
 */
public interface AsyncService {
    List<UserRes> asyncTest(User user);

    void send(String name);
}
