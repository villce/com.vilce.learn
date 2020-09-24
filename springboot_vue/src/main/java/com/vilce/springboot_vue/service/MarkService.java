package com.vilce.springboot_vue.service;

import com.vilce.common.model.po.Mark;
import com.vilce.common.model.po.Text;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Description: 图片相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.MarkService
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
     * @param multipartFile 图片
     * @param text          文字水印参数
     * @param imageType     图片类型
     * @return
     */
    String markWordToImage(MultipartFile multipartFile, Text text, String imageType);

    /**
     * 给图片加图片水印
     *
     * @param icon      背景图片
     * @param source    水印图片
     * @param mark      水印参数
     * @param imageType 图片类型
     * @return
     */
    String markImageToImage(MultipartFile icon, MultipartFile source, Mark mark, String imageType);
}
