package com.vilce.springboot_vue.module.secret.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.secret.model
 * @Author: 雷才哲
 * @Date: 2021/3/2 下午5:27
 * @Version: 1.0
 */
@ApiModel(value = "图片模块入参")
public class ModulesReq {
    @ApiModelProperty(value = "系统物理主键")
    private int id;
    @ApiModelProperty(value = "模块日期", example = "2021-03-02")
    private String modulesDate;
    @ApiModelProperty(value = "模块标题", example = "测试")
    private String modulesTitle;
    @ApiModelProperty(value = "图片地址", example = "[\"http://loalhost:8006/img/2021-03-02/202103020001.png\"]")
    private List<String> imgUrlList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModulesDate() {
        return modulesDate;
    }

    public void setModulesDate(String modulesDate) {
        this.modulesDate = modulesDate;
    }

    public String getModulesTitle() {
        return modulesTitle;
    }

    public void setModulesTitle(String modulesTitle) {
        this.modulesTitle = modulesTitle;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }
}
