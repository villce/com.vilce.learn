package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.vo.request.UserReq;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.UserService
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:39
 * @Version: 1.0
 */
public interface UserService {
    boolean login(UserReq req);
}
