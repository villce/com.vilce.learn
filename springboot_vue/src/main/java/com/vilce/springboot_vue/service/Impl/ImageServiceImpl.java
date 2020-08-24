package com.vilce.springboot_vue.service.Impl;

import com.vilce.springboot_vue.service.ImageService;
import com.vilce.springboot_vue.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.ImageServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/24 16:27
 * @Version: 1.0
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String coversUpload(MultipartFile file) {
        String folder = "D:/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, FileUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8006/image/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
