package com.vilce.springboot_vue.dao;

import com.vilce.springboot_vue.model.po.User;
import com.vilce.springboot_vue.model.vo.request.UserReq;
import org.springframework.stereotype.Component;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.dao.UserMapper
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:39
 * @Version: 1.0
 */
@Component
public interface UserMapper {
    User getUserByNamePassword(UserReq req);
}
