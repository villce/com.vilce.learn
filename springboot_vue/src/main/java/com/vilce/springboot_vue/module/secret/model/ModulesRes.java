package com.vilce.springboot_vue.module.secret.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.secret.model
 * @Author: 雷才哲
 * @Date: 2021/3/3 上午10:57
 * @Version: 1.0
 */
@ApiModel(value = "模块返参")
public class ModulesRes {
    @ApiModelProperty(value = "图片模块")
    private List<Modules> modulesList;
    @ApiModelProperty(value = "模块数量")
    private int num;

    public List<Modules> getModulesList() {
        return modulesList;
    }

    public void setModulesList(List<Modules> modulesList) {
        this.modulesList = modulesList;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
