package com.vilce.springboot_vue.module.secret.controller;

import com.vilce.springboot_vue.module.secret.model.Modules;
import com.vilce.springboot_vue.module.secret.model.ModulesReq;
import com.vilce.springboot_vue.module.secret.model.ModulesRes;
import com.vilce.springboot_vue.module.secret.service.SecretService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.secret
 * @Author: 雷才哲
 * @Date: 2021/3/2 下午5:23
 * @Version: 1.0
 */
@RestController
@RequestMapping("secret")
@Api(tags = "秘密花园接口")
public class SecretController {

    @Autowired
    private SecretService secretService;

    @PostMapping("editModules")
    @ApiOperation(value = "编辑图片模块")
    public void editModules(@RequestBody ModulesReq req) {
        if (req.getId() == 0) {
            secretService.createModules(req.getModulesDate(), req.getModulesTitle(), req.getImgList());
        }else {
            secretService.updateModules(req.getId(), req.getModulesDate(), req.getModulesTitle(), req.getImgList());
        }
    }

    @GetMapping("deleteModules/{modulesId}")
    @ApiOperation(value = "删除图片模块")
    public void deleteModules(@PathVariable int modulesId) {
        secretService.deleteModules(modulesId);
    }

    @GetMapping("getModules/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页获取图片模块")
    public ModulesRes getModules(@PathVariable int pageIndex, @PathVariable int pageSize) {
        return secretService.getModules(pageIndex, pageSize);
    }

    @GetMapping("findModules/{modulesId}")
    @ApiOperation(value = "根据ID查询模块")
    public Modules findModules(@PathVariable int modulesId) {
        return secretService.findModules(modulesId);
    }

    @GetMapping("getNewModules/{pageSize}")
    @ApiOperation(value = "获取最新的模块")
    public ModulesRes getNewModules(@PathVariable int pageSize) {
        return secretService.getNewModules(pageSize);
    }

    @GetMapping("timeLineGetModules/{pageIndex}/{pageSize}")
    @ApiOperation(value = "时间轴分段获取模块")
    public ModulesRes timeLineGetModules(@PathVariable int pageIndex, @PathVariable int pageSize) {
        return secretService.timeLineGetModules(pageIndex, pageSize);
    }

    @PostMapping("coversImage")
    @ApiOperation(value = "上传压缩图片")
    public String coversImage(MultipartFile file) {
        return secretService.coversImage(file);
    }
}
