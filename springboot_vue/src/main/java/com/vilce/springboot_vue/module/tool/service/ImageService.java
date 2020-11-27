package com.vilce.springboot_vue.module.tool.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.tool.service.ImageService
 * @Author: 雷才哲
 * @Date: 2020/11/26 11:03
 * @Version: 1.0
 */
public interface ImageService {
    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    String coversUpload(MultipartFile file);

    /**
     * 删除指定路径图片
     *
     * @param imageUrl 图片路径
     * @return
     */
    boolean deleteImage(String imageUrl);
}
