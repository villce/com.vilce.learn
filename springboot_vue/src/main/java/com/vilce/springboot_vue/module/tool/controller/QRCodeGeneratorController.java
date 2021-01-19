package com.vilce.springboot_vue.module.tool.controller;

import com.vilce.springboot_vue.utils.QRCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.tool.controller.QRCodeGeneratorController
 * @Author: 雷才哲
 * @Date: 2021/1/19 13:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/qrCode")
public class QRCodeGeneratorController {

    @GetMapping("/generator")
    public void encodeQrCode(String codeContent, HttpServletResponse response) {
        // 嵌入二维码的图片路径
        String imgPath = "D:\\image\\icon.jpg";
        try {
            QRCodeUtils.encode(codeContent, imgPath, true, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
