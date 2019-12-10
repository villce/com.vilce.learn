package com.vilce.image.service.Impl;

import cn.easyproject.easyocr.EasyOCR;
import cn.easyproject.easyocr.ImageType;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.image.service.Impl.VerificationCodeServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/9 15:45
 * @Version: 1.0
 */
@Service
public class VerificationCodeServiceImpl {
    public static void main(String[] args) {
        try {
            File file = ResourceUtils.getFile("classpath:code2.png");
            code2txt(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String code2txt(File file) throws FileNotFoundException {

        EasyOCR ocr = new EasyOCR();
        //直接识别验证码图片内容
        System.out.println("CAPTCHA_NORMAL: " + ocr.discernAndAutoCleanImage(file, ImageType.CAPTCHA_NORMAL));
        System.out.println("CAPTCHA_INTERFERENCE_LINE: " + ocr.discernAndAutoCleanImage(file, ImageType.CAPTCHA_INTERFERENCE_LINE));
        System.out.println("CAPTCHA_SPOT: " + ocr.discernAndAutoCleanImage(file, ImageType.CAPTCHA_SPOT));
        System.out.println("CAPTCHA_WHITE_CHAR: " + ocr.discernAndAutoCleanImage(file, ImageType.CAPTCHA_WHITE_CHAR));
        System.out.println("CAPTCHA_HOLLOW_CHAR: " + ocr.discernAndAutoCleanImage(file, ImageType.CAPTCHA_HOLLOW_CHAR));
        System.out.println("CLEAR: " + ocr.discernAndAutoCleanImage(file, ImageType.CLEAR));
        System.out.println("NONE: " + ocr.discernAndAutoCleanImage(file, ImageType.NONE));

        return null;
    }
}
