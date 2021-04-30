package com.vilce.springboot_vue.utils;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.utils.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Description: 图片工具
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.utils
 * @Author: 雷才哲
 * @Date: 2021/4/26 下午2:12
 * @Version: 1.0
 */
public class ImageUtils {

    /**
     * 去除图片背景
     *
     * @param multipartFile 源图片
     * @param accountKey    密钥
     * @return
     */
    public static InputStream removeBg(MultipartFile multipartFile, String accountKey) {
        String apiUrl = "https://api.remove.bg/v1.0/removebg";
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setHeader("X-Api-Key", accountKey);
        //建立一个NameValuePair数组，用于存储欲传送的参数
        File file = new File("sourceImage.png");
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileBody fileBody = new FileBody(file);
        HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("image_file", fileBody).build();
        try {
            httpPost.setEntity(reqEntity);
            HttpResponse response = new DefaultHttpClient().execute(httpPost);
            ByteArrayOutputStream image = new ByteArrayOutputStream();
            response.getEntity().writeTo(image);
            return new ByteArrayInputStream(image.toByteArray());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更换图片背景颜色
     *
     * @param multipartFile 源图片
     * @param accountKey    密钥
     * @return
     */
    public static InputStream changeBg(MultipartFile multipartFile, String accountKey, String bgColor) {
        String apiUrl = "https://api.remove.bg/v1.0/removebg";
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setHeader("X-Api-Key", accountKey);
        //建立一个NameValuePair数组，用于存储欲传送的参数
        File file = new File("sourceImage.png");
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileBody fileBody = new FileBody(file);
        HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("image_file", fileBody).addTextBody("bg_color", bgColor).build();
        try {
            httpPost.setEntity(reqEntity);
            HttpResponse response = new DefaultHttpClient().execute(httpPost);
            ByteArrayOutputStream image = new ByteArrayOutputStream();
            response.getEntity().writeTo(image);
            return new ByteArrayInputStream(image.toByteArray());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 指定图片宽度和高度或压缩比例对图片进行压缩
     *
     * @param sourceImg 源图片
     * @param width     宽
     * @param height    高
     * @param rate      比例
     */
    public static byte[] reduceImg(InputStream sourceImg, int width, int height, Float rate) {
        try {
            Image image = ImageIO.read(sourceImg);
            // 如果比例不为空则说明是按比例压缩
            if (rate != null && rate > 0) {
                //获得源图片的宽高存入数组中

                int widthSrc = image.getWidth(null);
                int heightSrc = image.getHeight(null);
                //按比例缩放或扩大图片大小，将浮点型转为整型
                width = (int) (widthSrc * rate);
                height = (int) (heightSrc * rate);
            }
            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            //创建文件输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(tag,"jpeg",out);
            return out.toByteArray();
        } catch (Exception ef) {
            throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "图片压缩失败！");
        }
    }
}
