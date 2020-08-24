package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.service.ImageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.ImageController
 * @Author: 雷才哲
 * @Date: 2020/8/24 16:24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/image")
@Api("图片API")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("covers")
    public String coversUpload(MultipartFile file) {
       return imageService.coversUpload(file);
    }

    @GetMapping("file")
    public File getFile(String url) {
        return new File(url);
    }
}
