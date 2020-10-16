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
    @ApiModelProperty("文字旋转角度：0：0°，1:45°，2：-45°，3：90°，4：-90°")
    private Integer degree;
    @ApiModelProperty("最终水印文件地址，默认为D:/img")
    private String output;
    @ApiModelProperty("最终水印文件名称")
    private String fileName;
    @ApiModelProperty("单个水印或铺满")
    private boolean single;
    @ApiModelProperty("单个水印时，水印位置：0-左上，1-右上，2-左下，3-右下，4-中间")
    private Integer position;

    public int getAngle (Integer degree) {
        int angle = 0;
        switch (degree) {
            case 1:
                angle = 45;
                break;
            case 2:
                angle = -45;
                break;
            case 3:
                angle = 90;
                break;
            case 4:
                angle = -90;
                break;
        }
        return angle;
    }
}
