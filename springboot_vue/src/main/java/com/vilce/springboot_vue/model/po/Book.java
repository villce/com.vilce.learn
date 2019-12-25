package com.vilce.springboot_vue.model.po;

import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.po.Book
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:22
 * @Version: 1.0
 */
@Data
public class Book {
    private Long eid;
    private String cover;
    private String title;
    private String author;
    private String time;
    private String press;
    private String introduction;
    private Long cid;
}
