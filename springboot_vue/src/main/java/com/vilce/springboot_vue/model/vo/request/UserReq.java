package com.vilce.springboot_vue.model.vo.request;

import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.vo.request.UserReq
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:41
 * @Version: 1.0
 */
@Data
public class UserReq {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
}
