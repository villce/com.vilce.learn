package com.vilce.springboot_vue.module.tool.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 文字对象
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.po.Text
 * @Author: 雷才哲
 * @Date: 2020/9/23 17:27
 * @Version: 1.0
 */
@ApiModel(value = "文字")
public class Text extends Mark {
    @ApiModelProperty("水印文字，多行请换行输入")
    private String word;
    @ApiModelProperty("文字大小")
    private int wordSize;
    @ApiModelProperty("文字颜色")
    private String color;
    @ApiModelProperty("x轴偏移量")
    private double changeX;
    @ApiModelProperty("y轴偏移量")
    private double changeY;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getWordSize() {
        return wordSize;
    }

    public void setWordSize(int wordSize) {
        this.wordSize = wordSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getChangeX() {
        return changeX;
    }

    public void setChangeX(double changeX) {
        this.changeX = changeX;
    }

    public double getChangeY() {
        return changeY;
    }

    public void setChangeY(double changeY) {
        this.changeY = changeY;
    }
}
