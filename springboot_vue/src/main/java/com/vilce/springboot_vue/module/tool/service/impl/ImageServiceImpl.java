package com.vilce.springboot_vue.module.tool.service.impl;

import com.vilce.common.model.enums.DateEnum;
import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.utils.SpecialCharUtils;
import com.vilce.common.utils.io.FileUtils;
import com.vilce.springboot_vue.module.tool.model.ImageBackground;
import com.vilce.springboot_vue.module.tool.service.ImageService;
import com.vilce.springboot_vue.utils.ImageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.*;

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
            throw new BasicException(190001, "文件错误！");
        }
    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @Override
    public String coversUpload(MultipartFile file) {
        File imageFolder = new File(StringUtils.join(coversUrl, "temp"));
        File f = new File(imageFolder, DateFormatUtils.format(new Date(), DateEnum.YYYYMMDDHHMMSS.getFormat()) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURL = StringUtils.join(imageUrl, "temp/", f.getName());
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
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
                throw new BasicException(190001, "文件错误！");
            }
        }
        InputStream changeImage = ImageUtils.changeBg(sourceFile, accountKey, color);
        byte[] images = ImageUtils.reduceImg(changeImage, width, height, null);
        Base64.Encoder encoder = Base64.getEncoder();
        String data = encoder.encodeToString(images);
        return data;
    }


}
