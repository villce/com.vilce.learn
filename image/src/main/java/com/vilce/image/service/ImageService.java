package com.vilce.image.service;

import java.io.File;
import java.io.IOException;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.image.service.ImageService
 * @Author: 雷才哲
 * @Date: 2019/12/9 15:36
 * @Version: 1.0
 */
public interface ImageService {
    File save(byte[] bytes, String originalFilename) throws IOException;

    String getErrorPath();
}
