package com.vilce.springboot_vue.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "图书")
public class Book {
    @ApiModelProperty(value = "图书id",example = "0")
    private int id;
    @ApiModelProperty(value = "封面url",example = "d:/img/1.png")
    private String cover;
    @ApiModelProperty(value = "书名",example = "示例")
    private String title;
    @ApiModelProperty(value = "作者",example = "示例")
    private String author;
    @ApiModelProperty(value = "出版日期",example = "2020.08.26")
    private String date;
    @ApiModelProperty(value = "出版社",example = "示例")
    private String press;
    @ApiModelProperty(value = "简介",example = "示例")
    private String abs;
    @ApiModelProperty(value = "分类cid",example = "0")
    private int cid;
}
