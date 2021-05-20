package com.vilce.springboot_vue.module.tool.service.impl;

import com.google.common.collect.Lists;
import com.vilce.common.model.enums.DateEnum;
import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.utils.io.FileUtils;
import com.vilce.springboot_vue.module.tool.model.res.ImageRes;
import com.vilce.springboot_vue.module.tool.service.ImageService;
import com.vilce.springboot_vue.utils.ImageUtils;
import com.vilce.springboot_vue.utils.NumberUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.tool.service.impl.ImageServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/11/26 11:03
 * @Version: 1.0
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${covers.url}")
    private String coversUrl;
    @Value("${com.vilce.image.url}")
    private String imageUrl;
    @Value("${com.vilce.image.accountKey}")
    private String accountKey;

    /**
     * 工具上传图片（伪上传）
     *
     * @param file 图片
     * @return
     */
    @Override
    public String toolUpload(MultipartFile file) {
        try {
            // 判断是否为图片
            Image image = ImageIO.read(file.getInputStream());
            return "成功";
        } catch (IOException e) {
            throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "文件错误！");
        }
    }

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     */
    @Override
    public ImageRes imageUpload(MultipartFile multipartFile) {
        String dateStr = DateFormatUtils.format(new Date(), DateEnum.YYYY_MM.getFormat());
        String sourcePath = StringUtils.join(coversUrl, dateStr, "/source");
        File sourcePathDir = new File(sourcePath);
        if (!sourcePathDir.exists()) {
            sourcePathDir.mkdirs();
        }
        String sourceUrl = StringUtils.join(sourcePath + "/" + multipartFile.getOriginalFilename());
        File file = new File(sourceUrl);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "文件错误！");
        }
        ImageRes imageRes = new ImageRes();
        imageRes.setSourceImageUrl(sourceUrl);
        imageRes.setSourceImageName(multipartFile.getOriginalFilename());
        imageRes.setSourceImageSize(getSize(multipartFile.getSize()));
        return imageRes;
    }

    /**
     * 删除指定路径图片
     *
     * @param imageName 图片名
     * @return
     */
    @Override
    public boolean deleteImage(String imageName) {
        if (StringUtils.isNotEmpty(imageName)) {
            // 获取文件名
            File file = new File(StringUtils.join(coversUrl, "temp/" + imageName));
            return FileUtils.deleteQuietly(file);
        }
        return false;
    }

    /**
     * 更改图片背景颜色
     *
     * @param sourceFile 源图片
     * @param width      宽
     * @param height     长
     * @param color      背景颜色
     * @return
     */
    @Override
    public String changBg(MultipartFile sourceFile, int width, int height, String color) {
        if (width == 0 || height == 0) {
            try {
                // 判断是否为图片
                Image image = ImageIO.read(sourceFile.getInputStream());
                width = image.getWidth(null);
                height = image.getHeight(null);
            } catch (IOException e) {
                throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "文件错误！");
            }
        }
        InputStream changeImage = ImageUtils.changeBg(sourceFile, accountKey, color);
        byte[] images = ImageUtils.reduceImg(changeImage, width, height, null);
        Base64.Encoder encoder = Base64.getEncoder();
        String data = encoder.encodeToString(images);
        return data;
    }

    /**
     * 压缩图片
     *
     * @param imageList 源图片
     * @param scale     长宽
     * @param quality   质量
     * @return
     */
    @Override
    public List<ImageRes> compress(List<ImageRes> imageList, int scale, int quality) {
        List<ImageRes> imageResList = Lists.newLinkedList();
        String dateStr = DateFormatUtils.format(new Date(), DateEnum.YYYY_MM.getFormat());
        String compressPath = StringUtils.join(coversUrl, dateStr);
        File compressPathDir = new File(compressPath);
        if (!compressPathDir.exists()) {
            compressPathDir.mkdirs();
        }
        imageList.forEach(image -> {
            ImageRes imageRes = new ImageRes();
            imageRes.setSourceImageName(image.getSourceImageName());
            imageRes.setSourceImageSize(image.getSourceImageSize());
            imageRes.setSourceImageUrl(image.getSourceImageUrl());
            imageRes.setCompressImageUrl(StringUtils.join(imageUrl, dateStr, "/", image.getSourceImageName()));
            String compressUrl = StringUtils.join(compressPath, "/", image.getSourceImageName());
            File file = new File(image.getSourceImageUrl());
            try {
                Thumbnails.of(file)
                        //图片大小（长宽）压缩比例 从0-1，1表示原图
                        .scale(Float.valueOf(scale) / 100)
                        //图片质量压缩比例 从0-1，越接近1质量越好
                        .outputQuality(Float.valueOf(quality) / 100)
                        .toOutputStream(new FileOutputStream(compressUrl));
            } catch (IOException e) {
                throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "文件错误！");
            }
            File compressFile = new File(compressUrl);
            imageRes.setCompressImageSize(getSize(compressFile.length()));
            imageResList.add(imageRes);
        });
        return imageResList;
    }

    /**
     * 换算图片大小（带单位）
     *
     * @param size
     * @return
     */
    public String getSize(long size) {
        if (size < 1024) {
            return StringUtils.join(size, "B");
        } else if (size < 1048576) {
            double imageSize = Float.valueOf(size) / 1024;
            return StringUtils.join(NumberUtils.rounding(imageSize, 2), "K");
        } else {
            double imageSize = Float.valueOf(size) / 1048576;
            return StringUtils.join(NumberUtils.rounding(imageSize, 2), "M");
        }
    }
}
