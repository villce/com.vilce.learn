package com.vilce.springboot_vue.module.tool.service;

import com.vilce.springboot_vue.module.tool.model.res.ImageRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * 工具上传图片（伪上传）
     *
     * @param file 图片
     * @return
     */
    String toolUpload(MultipartFile file);

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    ImageRes imageUpload(MultipartFile file);

    /**
     * 删除指定路径图片
     *
     * @param imageName 图片名
     * @return
     */
    boolean deleteImage(String imageName);

    /**
     * 更改图片背景颜色
     *
     * @param sourceFile 源图片
     * @param width      宽
     * @param height     长
     * @param color      背景颜色
     * @return
     */
    String changBg(MultipartFile sourceFile, int width, int height, String color);

    /**
     * 压缩图片
     *
     * @param imageList 源图片
     * @param scale     长宽
     * @param quality   质量
     * @return
     */
    List<ImageRes> compress(List<ImageRes> imageList, int scale, int quality);
}
