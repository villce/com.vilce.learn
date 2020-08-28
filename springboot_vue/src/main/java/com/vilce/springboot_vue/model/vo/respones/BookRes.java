package com.vilce.springboot_vue.model.vo.respones;

import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.po.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.vo.respones.BookRes
 * @Author: 雷才哲
 * @Date: 2019/12/23 14:16
 * @Version: 1.0
 */
@Data
@ApiModel("书籍返参")
public class BookRes {
    @ApiModelProperty(value = "系统物理主键",example = "0")
    private int id;
    @ApiModelProperty(value = "图片封面",example = "d:/img/1.png")
    private String cover;
    @ApiModelProperty(value = "书名",example = "示例")
    private String title;
    @ApiModelProperty(value = "作者",example = "示例")
    private String author;
    @ApiModelProperty(value = "时间",example = "2020.08.26")
    private String date;
    @ApiModelProperty(value = "出版社",example = "示例")
    private String press;
    @ApiModelProperty(value = "简介",example = "示例")
    private String abs;
    @ApiModelProperty(value = "种类",example = "{}")
    private Category category;

    public static BookRes create(Book book, Category category){
        if (ObjectUtils.isEmpty(book)) {
            return null;
        }
        //将属性和名称相似的值进行拷贝
        BeanCopier beanCopier = BeanCopier.create(Book.class, BookRes.class, false);
        BookRes bookRes = new BookRes();
        beanCopier.copy(book, bookRes, null);
        bookRes.setCategory(category);
        return bookRes;
    }
}
