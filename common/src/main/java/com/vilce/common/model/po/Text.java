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
    @ApiModelProperty("水印文字")
    private String word;
    @ApiModelProperty("文字大小")
    private int wordSize;
    @ApiModelProperty("文字颜色：0-白，1-黑，2-灰，3-9为彩虹色")
    private int color;
}
