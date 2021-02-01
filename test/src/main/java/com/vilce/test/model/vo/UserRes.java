package com.vilce.test.model.vo;

import io.swagger.annotations.ApiModel;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.model.vo.UserRes
 * @Author: 雷才哲
 * @Date: 2019/11/13 15:49
 * @Version: 1.0
 */
@ApiModel("用户")
public class UserRes {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
