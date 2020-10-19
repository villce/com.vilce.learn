package com.vilce.springboot_vue.controller;

import com.vilce.common.model.po.Mark;
import com.vilce.common.model.po.Text;
import com.vilce.springboot_vue.service.MarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

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
    public String markNew(Text text) {
        return markService.markNew(text);
    }

    @PostMapping("wordToImage")
    @ApiOperation(value = "给图片添加文字水印")
    public String markWordToImage(MultipartFile multipartFile, Text text) {
        return markService.markWordToImage(multipartFile, text);
    }

    @PostMapping("imageToImage")
    @ApiOperation(value = "给图片加图片水印")
    public String markImageToImage(MultipartFile icon, MultipartFile source, Mark mark) {
        return markService.markImageToImage(icon, source, mark);
    }
}
