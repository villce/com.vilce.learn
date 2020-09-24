package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.model.po.Text;
import com.vilce.common.utils.MarkImageUtils;
import com.vilce.common.model.po.Mark;
import com.vilce.springboot_vue.service.MarkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 图片相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.ImageServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/9/23 13:41
 * @Version: 1.0
 */
@Service
public class MarkServiceImpl implements MarkService {

    @Value("${covers.url}")
    private String DEFAULT_OUTPUT;
    @Value("${image.type}")
    private final String DEFAULT_TYPE = "png";

    private final int DEFAULT_WIDTH = 2040;
    private final int DEFAULT_HEIGHT = 1020;

    /**
     * 生成透明水印图片
     *
     * @param text 文字水印参数
     * @return
     */
    @Override
    public String markNew(Text text) {
        if (StringUtils.isEmpty(text.getOutput())) {
            text.setOutput(DEFAULT_OUTPUT);
        }
        return MarkImageUtils.markNewImage(text, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * 给图片添加文字水印
     *
     * @param multipartFile 图片
     * @param text          文字水印参数
     * @param imageType     图片类型
     * @return
     */
    @Override
    public String markWordToImage(MultipartFile multipartFile, Text text, String imageType) {
        if (StringUtils.isEmpty(text.getOutput())) {
            text.setOutput(DEFAULT_OUTPUT);
        }
        if (StringUtils.isEmpty(imageType)) {
            imageType = DEFAULT_TYPE;
        }
        String result = MarkImageUtils.markImageByMoreText(multipartFile, text, imageType);
        return result;
    }

    /**
     * 给图片加图片水印
     *
     * @param icon      背景图片
     * @param source    水印图片
     * @param mark      水印参数
     * @param imageType 图片类型
     * @return
     */
    @Override
    public String markImageToImage(MultipartFile icon, MultipartFile source, Mark mark, String imageType) {
        if (StringUtils.isEmpty(mark.getOutput())) {
            mark.setOutput(DEFAULT_OUTPUT);
        }
        if (StringUtils.isEmpty(imageType)) {
            imageType = DEFAULT_TYPE;
        }
        return MarkImageUtils.markImageByMoreIcon(icon, source, mark, imageType);
    }
}
