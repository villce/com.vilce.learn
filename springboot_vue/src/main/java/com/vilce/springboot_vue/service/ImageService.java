package com.vilce.springboot_vue.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.ImageService
 * @Author: 雷才哲
 * @Date: 2020/8/24 16:27
 * @Version: 1.0
 */
public interface ImageService {
    String coversUpload(MultipartFile file);
}
