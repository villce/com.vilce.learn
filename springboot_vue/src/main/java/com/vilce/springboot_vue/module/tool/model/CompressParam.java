package com.vilce.springboot_vue.module.tool.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.tool.model
 * @Author: 雷才哲
 * @Date: 2021/5/18 上午10:39
 * @Version: 1.0
 */
@ApiModel(value = "压缩图片参数")
public class CompressParam {
    @ApiModelProperty(value = "压缩长宽比")
    private double scale;
    @ApiModelProperty(value = "压缩质量比")
    private double quality;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }
}
