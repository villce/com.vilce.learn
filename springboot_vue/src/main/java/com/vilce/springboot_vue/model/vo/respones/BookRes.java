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
    @ApiModelProperty("系统物理主键")
    private Long eid;
    @ApiModelProperty("图片封面")
    private String cover;
    @ApiModelProperty("书名")
    private String title;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("时间")
    private String time;
    @ApiModelProperty("出版社")
    private String press;
    @ApiModelProperty("简介")
    private String introduction;
    @ApiModelProperty("种类")
    private Category category;

    public static BookRes create(Book book, Category category){
        if (ObjectUtils.isEmpty(book)) {
            return new BookRes();
        }
        //将属性和名称相似的值进行拷贝
        BeanCopier beanCopier = BeanCopier.create(Book.class, BookRes.class, false);
        BookRes bookRes = new BookRes();
        beanCopier.copy(book, bookRes, null);
        bookRes.setCategory(category);
        return bookRes;
    }
}
