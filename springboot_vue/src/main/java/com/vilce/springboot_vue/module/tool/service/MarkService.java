package com.vilce.springboot_vue.module.tool.service;

import com.vilce.springboot_vue.module.tool.model.Mark;
import com.vilce.springboot_vue.module.tool.model.Text;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Description: 图片相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.tool.service.MarkService
 * @Author: 雷才哲
 * @Date: 2020/9/23 13:40
 * @Version: 1.0
 */
public interface MarkService {

    /**
     * 生成透明水印图片
     *
     * @param text 文字水印参数
     * @return
     */
    String markNew(Text text);

    /**
     * 给图片添加文字水印
     *
     * @param sourceFile 图片
     * @param text       文字水印参数
     * @return
     */
    String markWordToImage(MultipartFile sourceFile, Text text);

    /**
     * 给图片加图片水印
     *
     * @param iconFile   背景图片
     * @param sourceFile 水印图片
     * @param mark       水印参数
     * @return
     */
    String markImageToImage(MultipartFile iconFile, MultipartFile sourceFile, Mark mark);
}
