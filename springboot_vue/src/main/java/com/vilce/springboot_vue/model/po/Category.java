package com.vilce.springboot_vue.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 书籍分类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.po.Category
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:21
 * @Version: 1.0
 */
@Data
public class Category implements Serializable {
    /**
     * 编号
     */
    private Long eid;
    /**
     * 分类名
     */
    private String name;
}
