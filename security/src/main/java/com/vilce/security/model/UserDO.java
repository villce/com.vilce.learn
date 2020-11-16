package com.vilce.security.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.model.UserDO
 * @Author: 雷才哲
 * @Date: 2019/12/11 11:24
 * @Version: 1.0
 */
@Data
public class UserDO {
    private Integer id;

    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
}
