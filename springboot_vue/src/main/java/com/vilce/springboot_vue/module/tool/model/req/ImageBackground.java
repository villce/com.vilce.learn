package com.vilce.springboot_vue.module.tool.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.tool.model
 * @Author: 雷才哲
 * @Date: 2021/4/26 下午2:52
 * @Version: 1.0
 */
@ApiModel(value = "图片背景入参")
public class ImageBackground {
    @ApiModelProperty(value = "背景颜色")
    private String color;
    @ApiModelProperty(value = "宽")
    private int width;
    @ApiModelProperty(value = "长")
    private int height;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
