package com.vilce.common.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 水印参数
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.po.Mark
 * @Author: 雷才哲
 * @Date: 2020/9/23 13:52
 * @Version: 1.0
 */
@ApiModel(value = "水印参数")
@Data
public class Mark {
    @ApiModelProperty("文字旋转角度")
    private Integer degree;
    @ApiModelProperty("最终水印文件地址，默认为D:/img")
    private String output;
    @ApiModelProperty("最终水印文件名称")
    private String fileName;
    @ApiModelProperty("水印是否铺满")
    private boolean paved;
}
