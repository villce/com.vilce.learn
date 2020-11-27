package com.vilce.springboot_vue.module.tool.controller;

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
@RequestMapping("/image")
@Api(tags = "图片控制器")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("coversUpload")
    @ApiOperation(value = "上传封面图片")
    public String coversUpload(MultipartFile file) {
        return imageService.coversUpload(file);
    }

    @GetMapping("deleteImage")
    @ApiOperation(value = "删除指定路径图片")
    public boolean deleteImage(String imageUrl) {
        return imageService.deleteImage(imageUrl);
    }
}
