package com.vilce.annotation.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.model.vo.UserRes
 * @Author: 雷才哲
 * @Date: 2019/11/13 15:49
 * @Version: 1.0
 */
@Data
@ApiModel("用户")
public class UserRes {
    private String name;
    private Integer age;
}
