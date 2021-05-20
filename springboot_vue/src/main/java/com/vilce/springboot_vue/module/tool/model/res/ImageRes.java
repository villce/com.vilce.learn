package com.vilce.springboot_vue.module.tool.model.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.tool.model.res
 * @Author: 雷才哲
 * @Date: 2021/5/20 下午3:30
 * @Version: 1.0
 */
@ApiModel(value = "上传图片返参")
public class ImageRes {
    @ApiModelProperty(value = "原图片名")
    private String sourceImageName;
    @ApiModelProperty(value = "原图片大小")
    private String sourceImageSize;
    @ApiModelProperty(value = "原图片地址")
    private String sourceImageUrl;
    @ApiModelProperty(value = "压缩图片大小")
    private String compressImageSize;
    @ApiModelProperty(value = "压缩图片地址")
    private String compressImageUrl;

    public String getSourceImageName() {
        return sourceImageName;
    }

    public void setSourceImageName(String sourceImageName) {
        this.sourceImageName = sourceImageName;
    }

    public String getSourceImageSize() {
        return sourceImageSize;
    }

    public void setSourceImageSize(String sourceImageSize) {
        this.sourceImageSize = sourceImageSize;
    }

    public String getCompressImageSize() {
        return compressImageSize;
    }

    public void setCompressImageSize(String compressImageSize) {
        this.compressImageSize = compressImageSize;
    }

    public String getSourceImageUrl() {
        return sourceImageUrl;
    }

    public void setSourceImageUrl(String sourceImageUrl) {
        this.sourceImageUrl = sourceImageUrl;
    }

    public String getCompressImageUrl() {
        return compressImageUrl;
    }

    public void setCompressImageUrl(String compressImageUrl) {
        this.compressImageUrl = compressImageUrl;
    }
}
