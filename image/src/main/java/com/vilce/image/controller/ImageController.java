package com.vilce.image.controller;

import com.vilce.common.utils.LoggerUtils;
import com.vilce.image.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.image.controller.ImageController
 * @Author: 雷才哲
 * @Date: 2019/12/9 15:35
 * @Version: 1.0
 */
@RestController
@RequestMapping("/image")
@Api(tags = "图片接口")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "img2txt")
    public String toPage(){
        return "/imgUpload";
    }

    @ApiOperation(value = "图片转文本2")
    @PostMapping(value = "transfer")
    public ResponseEntity<InputStreamResource> imt2txt(@RequestParam("file") MultipartFile file){
        try {
            String originalFilename = file.getOriginalFilename();
            HttpHeaders headers = new HttpHeaders();

            // 支持jpg、png
            if(originalFilename.endsWith("jpg")||originalFilename.endsWith("png")){
                File outFile = imageService.save(file.getBytes(), originalFilename);
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", outFile.getName()));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentLength(outFile.length())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(new InputStreamResource(new FileInputStream(outFile)));
            }else{
                File error = new File(imageService.getErrorPath());
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", error.getName()));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentLength(error.length())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(new InputStreamResource(new FileInputStream(error)));
            }
        } catch (IOException e) {
            LoggerUtils.error(ImageController.class,e.getMessage());
            return new ResponseEntity("暂不支持的文件格式", HttpStatus.BAD_REQUEST);
        }
    //return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
