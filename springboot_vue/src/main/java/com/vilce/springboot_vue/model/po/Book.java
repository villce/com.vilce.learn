package com.vilce.springboot_vue.model.po;

import lombok.Data;

/**
 * @Description: 书
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.po.Book
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:22
 * @Version: 1.0
 */
@Data
public class Book {
    /**
     * 编号
     */
    private Long eid;
    /**
     * 封面
     */
    private String cover;
    /**
     * 书名
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版日期
     */
    private String time;
    /**
     * 出版社
     */
    private String press;
    /**
     * 简介
     */
    private String introduction;
    /**
     * 分类id
     */
    private Long cid;
}
