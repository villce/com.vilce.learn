package com.vilce.springboot_vue.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.vo.request.BookReq
 * @Author: 雷才哲
 * @Date: 2019/12/23 14:29
 * @Version: 1.0
 */
@Data
public class BookReq {
    @ApiModelProperty("系统物理主键")
    private Long eid;
    @ApiModelProperty("图片封面")
    private String cover;
    @ApiModelProperty("书名")
    @NotNull(message = "书名不能为空")
    private String title;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("时间")
    private String time;
    @ApiModelProperty("出版社")
    private String press;
    @ApiModelProperty("简介")
    private String introduction;
    @ApiModelProperty("种类id")
    @NotNull(message = "种类不能为空")
    private Long cid;
}
