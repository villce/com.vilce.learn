package com.vilce.springboot_vue.module.tool.model.req;

import com.vilce.springboot_vue.module.tool.model.res.ImageRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.tool.model
 * @Author: 雷才哲
 * @Date: 2021/5/18 上午10:39
 * @Version: 1.0
 */
@ApiModel(value = "压缩图片参数")
public class CompressReq {
    @ApiModelProperty(value = "压缩长宽比")
    private int scale;
    @ApiModelProperty(value = "压缩质量比")
    private int quality;
    @ApiModelProperty(value = "原图片")
    private List<ImageRes> imageList;

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public List<ImageRes> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageRes> imageList) {
        this.imageList = imageList;
    }
}
