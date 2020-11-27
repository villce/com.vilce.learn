package com.vilce.springboot_vue.module.tool.service.impl;

import com.vilce.common.utils.SpecialCharUtils;
import com.vilce.common.utils.io.FileUtils;
import com.vilce.springboot_vue.module.tool.service.ImageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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
    @Value("${image.url}")
    private String imageUrl;

    @Override
    public String coversUpload(MultipartFile file) {
        File imageFolder = new File(coversUrl);
        File f = new File(imageFolder, DateFormatUtils.format(new Date(), "yyyy-MM-dd-hh-mm-ss") + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURL = StringUtils.join(imageUrl, "/image/file/", f.getName());
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 删除指定路径图片
     *
     * @param imageUrl 图片路径
     * @return
     */
    @Override
    public boolean deleteImage(String imageUrl) {
        if (StringUtils.isNotEmpty(imageUrl)) {
            // 获取文件名
            String[] str = imageUrl.split(SpecialCharUtils.LEFT_SLASH);
            File file = new File(StringUtils.join(coversUrl, str[str.length - 1]));
            return FileUtils.deleteQuietly(file);
        }
        return false;
    }
}
