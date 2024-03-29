package com.vilce.springboot_vue.module.tool.service.impl;

import com.vilce.common.model.exception.BasicException;
import com.vilce.springboot_vue.module.tool.model.req.Mark;
import com.vilce.springboot_vue.module.tool.model.req.Text;
import com.vilce.springboot_vue.module.tool.service.MarkService;
import com.vilce.springboot_vue.utils.MarkImageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

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
        if (StringUtils.isEmpty(text.getFileName())) {
            text.setFileName(DateFormatUtils.format(new Date(), "yyyy-MM-dd-hh-mm-ss"));
        }
        byte[] image = null;
        if (text.isPaved()) {
            image = MarkImageUtils.markNewImageMore(text, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        } else {
            image = MarkImageUtils.markNewImageSingle(text, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
        Base64.Encoder encoder = Base64.getEncoder();
        String data = encoder.encodeToString(image);
        return data;
    }

    /**
     * 给图片添加文字水印
     *
     * @param sourceFile 图片
     * @param text       文字水印参数
     * @return
     */
    @Override
    public String markWordToImage(MultipartFile sourceFile, Text text) {
        if (StringUtils.isEmpty(text.getOutput())) {
            text.setOutput(DEFAULT_OUTPUT);
        }
        if (StringUtils.isEmpty(text.getFileName())) {
            text.setFileName(DateFormatUtils.format(new Date(), "yyyy-MM-dd-hh-mm-ss"));
        }
        byte[] image = null;
        try {
            if (text.isPaved()) {
                image = MarkImageUtils.markImageByMoreText(sourceFile.getInputStream(), text);
            } else {
                image = MarkImageUtils.markImageBySingleText(sourceFile.getInputStream(), text);
            }
        } catch (IOException e) {
            throw new BasicException(190001, "图片异常");
        }
        Base64.Encoder encoder = Base64.getEncoder();
        String data = encoder.encodeToString(image);
        return data;
    }

    /**
     * 给图片加图片水印
     *
     * @param iconFile   背景图片
     * @param sourceFile 水印图片
     * @param mark       水印参数
     * @return
     */
    @Override
    public String markImageToImage(MultipartFile iconFile, MultipartFile sourceFile, Mark mark) {
        if (StringUtils.isEmpty(mark.getOutput())) {
            mark.setOutput(DEFAULT_OUTPUT);
        }
        if (StringUtils.isEmpty(mark.getFileName())) {
            mark.setFileName(DateFormatUtils.format(new Date(), "yyyy-MM-dd-hh-mm-ss"));
        }
        byte[] image = null;
        try {
            if (mark.isPaved()) {
                image = MarkImageUtils.markImageByMoreIcon(iconFile.getInputStream(), sourceFile.getInputStream(), mark);
            } else {
                image = MarkImageUtils.markImageBySingleIcon(iconFile.getInputStream(), sourceFile.getInputStream(), mark);
            }
        } catch (IOException e) {
            throw new BasicException(190001, "图片异常");
        }
        Base64.Encoder encoder = Base64.getEncoder();
        String data = encoder.encodeToString(image);
        return data;
    }
}
