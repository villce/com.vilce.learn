package com.vilce.springboot_vue.module.tool.controller;

import com.vilce.common.model.po.Text;
import com.vilce.springboot_vue.module.tool.model.ImageBackground;
import com.vilce.springboot_vue.module.tool.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.tool.controller.ImageController
 * @Author: 雷才哲
 * @Date: 2020/11/26 11:02
 * @Version: 1.0
 */
@RestController
@RequestMapping("image")
@Api(tags = "图片控制器")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("toolUpload")
    @ApiOperation(value = "工具上传图片（伪上传）")
    public String toolUpload(MultipartFile file) {
        return imageService.toolUpload(file);
    }

    @PostMapping("coversUpload")
    @ApiOperation(value = "上传图片")
    public String coversUpload(MultipartFile file) {
        return imageService.coversUpload(file);
    }

    @GetMapping("deleteImage")
    @ApiOperation(value = "删除指定图片")
    public boolean deleteImage(String imageName) {
        return imageService.deleteImage(imageName);
    }

    @PostMapping("changBg")
    @ApiOperation(value = "更改图片背景颜色")
    public String changBg(MultipartFile sourceFile, ImageBackground background) {
        return imageService.changBg(sourceFile, background.getWidth(), background.getHeight(), background.getColor());
    }
}
