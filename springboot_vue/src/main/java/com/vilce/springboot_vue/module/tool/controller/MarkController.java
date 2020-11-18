package com.vilce.springboot_vue.module.tool.controller;

import com.vilce.common.model.po.Mark;
import com.vilce.common.model.po.Text;
import com.vilce.springboot_vue.module.tool.service.MarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 水印相关控制器
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.ImageController
 * @Author: 雷才哲
 * @Date: 2020/9/23 13:33
 * @Version: 1.0
 */
@Api(tags = "水印相关控制器")
@RestController
@RequestMapping("/mark")
public class MarkController {

    @Autowired
    private MarkService markService;

    @PostMapping("new")
    @ApiOperation(value = "生成透明水印图片")
    public String markNew(@RequestBody Text text) {
        return markService.markNew(text);
    }

    @PostMapping("wordToImage")
    @ApiOperation(value = "给图片添加文字水印")
    public String markWordToImage(MultipartFile sourceFile, Text text) {
        return markService.markWordToImage(sourceFile, text);
    }

    @PostMapping("imageToImage")
    @ApiOperation(value = "给图片加图片水印")
    public String markImageToImage(MultipartFile iconFile, MultipartFile sourceFile, Mark mark) {
        return markService.markImageToImage(iconFile, sourceFile, mark);
    }
}
