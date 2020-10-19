package com.vilce.common.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 文字对象
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.po.Text
 * @Author: 雷才哲
 * @Date: 2020/9/23 17:27
 * @Version: 1.0
 */
@ApiModel(value = "文字")
@Data
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
}
