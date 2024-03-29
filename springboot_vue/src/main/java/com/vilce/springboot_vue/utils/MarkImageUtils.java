package com.vilce.springboot_vue.utils;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.springboot_vue.module.tool.model.req.Mark;
import com.vilce.springboot_vue.module.tool.model.req.Text;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 图片水印工具
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.MarkImageUtils
 * @Author: 雷才哲
 * @Date: 2020/9/23 13:22
 * @Version: 1.0
 */
public class MarkImageUtils {

    /**
     * 生成一个新空白水印图片，水印铺满
     *
     * @param text   文字水印参数
     * @param width  图片宽度
     * @param height 图片高度
     * @return
     */
    public static byte[] markNewImageMore(Text text, int width, int height) {
        try {
            // 创建BufferedImage对象
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            // 获取Graphics2D
            Graphics2D g2d = image.createGraphics();
            // ---------- 增加下面的代码使得背景透明 -----------------
            image = g2d.getDeviceConfiguration().createCompatibleImage(width, height);
            g2d.dispose();
            g2d = image.createGraphics();
            // ---------- 背景透明代码结束 -----------------
            //抗锯齿 让图片看起来更清晰
            RenderingHints rh = g2d.getRenderingHints();
            rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(rh);
            // 画图 Start
            // 设置字体 名称、样式、磅值大小（同时使用斜体和粗体）
            Font font = new Font("宋体", Font.PLAIN, text.getWordSize());
            g2d.setFont(font);
            // 设置字体颜色
            g2d.setColor(Color.decode(text.getColor()));
            int x = 0;
            String first = text.getWord();
            String second = null;
            int space = height / text.getWordSize();
            int len = width / text.getWordSize() * text.getWord().length();
            if (null != text.getDegree()) {
                //设置水印旋转
                g2d.rotate(Math.toRadians(text.getDegree()), (double) image.getWidth() / 2, (double) image.getHeight() / 2);
            }
            if (first.length() > 20) {
                first = text.getWord().substring(0, 20);
                second = text.getWord().substring(20);
                len = width / text.getWordSize() * text.getWord().length() * 2;
            }
            for (int j = 0; j < len; j++) {
                int y = text.getWordSize();
                for (int i = 0; i < space; i++) {
                    g2d.setFont(font);
                    //水印位置
                    g2d.drawString(first, x, y);
                    if (StringUtils.isNotEmpty(second)) {
                        float move = 10 - second.length() / 2;
                        g2d.drawString(second, x + move * text.getWordSize(), y + text.getWordSize());
                    }
                    y += (4 * text.getWordSize());
                }
                x += (2 * text.getWordSize() * text.getWord().length());
            }
            // 画图 End
            // 释放对象
            g2d.dispose();
            //输出图片
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 保存文件
            ImageIO.write(image, "PNG", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BasicException(ResultStatus.ERROR.getStatus(), "生成一个新空白水印图片失败：" + e.getMessage());
        }
    }

    /**
     * 生成一个新空白水印图片，单个水印
     *
     * @param text   文字水印参数
     * @param width  图片宽度
     * @param height 图片高度
     * @return
     */
    public static byte[] markNewImageSingle(Text text, int width, int height) {
        try {
            // 创建BufferedImage对象
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            // 获取Graphics2D
            Graphics2D g2d = image.createGraphics();
            // ---------- 增加下面的代码使得背景透明 -----------------
            image = g2d.getDeviceConfiguration().createCompatibleImage(width, height);
            g2d.dispose();
            g2d = image.createGraphics();
            // ---------- 背景透明代码结束 -----------------
            //抗锯齿 让图片看起来更清晰
            RenderingHints rh = g2d.getRenderingHints();
            rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(rh);
            // 画图 Start
            // 设置字体 名称、样式、磅值大小（同时使用斜体和粗体）
            Font font = new Font("宋体", Font.PLAIN, text.getWordSize());
            g2d.setFont(font);
            // 设置字体颜色
            g2d.setColor(Color.decode(text.getColor()));
            //设置水印旋转
            g2d.rotate(Math.toRadians(text.getDegree()), (double) image.getWidth() / 2, (double) image.getHeight() / 2);
            // 获取水印文字，判断是否为多行
            String textString = text.getWord();
            String[] texts = textString.split("\n");
            int x = width / 2;
            int y = height / 2;
            //根据行数调整水印位置
            int len = texts.length;
            for (int i = 0; i < len; i++) {
                g2d.drawString(texts[i], (float) (x - text.getWordSize() * (texts[i].length() / 2) + text.getChangeX()),
                        (float) (y + (i + 1) * text.getWordSize() + text.getChangeY()));
            }
            // 画图 End
            // 释放对象
            g2d.dispose();
            //输出图片
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 保存文件
            ImageIO.write(image, "PNG", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BasicException(ResultStatus.ERROR.getStatus(), "生成一个新空白水印图片失败：" + e.getMessage());
        }
    }

    /**
     * 为图片添加文字水印
     *
     * @param sourceFile 源图片
     * @param text       文字水印参数
     * @return
     */
    public static byte[] markImageByMoreText(InputStream sourceFile, Text text) {
        try {
            Image img = ImageIO.read(sourceFile);
            //图片宽
            int width = img.getWidth(null);
            //图片高
            int height = img.getHeight(null);
            //加水印
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            g.drawImage(img, 0, 0, width, height, null);

            //抗锯齿 让图片看起来更清晰
            RenderingHints rh = g.getRenderingHints();
            rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHints(rh);

            //设置水印字体样式
            Font font = new Font("宋体", Font.PLAIN, text.getWordSize());
            //根据图片的背景设置水印颜色
            g.setColor(Color.decode(text.getColor()));
            int x = 0;
            int space = height / text.getWordSize();
            int len = width / text.getWordSize() * text.getWord().length();
            //设置水印旋转
            g.rotate(Math.toRadians(text.getDegree()), (double) bi.getWidth() / 2, (double) bi.getHeight() / 2);
            for (int j = 0; j < len; j++) {
                int y = text.getWordSize();
                for (int i = 0; i < space; i++) {
                    g.setFont(font);
                    //水印位置
                    g.drawString(text.getWord(), x, y);
                    y += (4 * text.getWordSize());
                }
                x += (2 * text.getWordSize() * text.getWord().length());
            }
            g.dispose();
            //输出图片
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 保存文件
            ImageIO.write(bi, "PNG", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "水印添加失败：" + e.getMessage());
        }
    }

    /**
     * 给图片添加单个文字水印、可设置水印文字旋转角度
     *
     * @param sourceFile 源图片
     * @param text       文字水印参数
     */
    public static byte[] markImageBySingleText(InputStream sourceFile, Text text) {
        try {
            Image img = ImageIO.read(sourceFile);
            int width = img.getWidth(null);
            int height = img.getHeight(null);
            //加水印
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            g.drawImage(img, 0, 0, width, height, null);
            // 画图 Start
            // 设置字体 名称、样式、磅值大小（同时使用斜体和粗体）
            Font font = new Font("宋体", Font.PLAIN, text.getWordSize());
            g.setFont(font);
            // 设置字体颜色
            g.setColor(Color.decode(text.getColor()));
            //设置水印旋转
            g.rotate(Math.toRadians(text.getDegree()), (double) bi.getWidth() / 2, (double) bi.getHeight() / 2);
            // 获取水印文字，判断是否为多行
            String textString = text.getWord();
            String[] texts = textString.split("\n");
            int x = width / 2;
            int y = height / 2;
            //根据行数调整水印位置
            int len = texts.length;
            for (int i = 0; i < len; i++) {
                g.drawString(texts[i], (float) (x - text.getWordSize() * (texts[i].length() / 2) + text.getChangeX()),
                        (float) (y + (i + 1) * text.getWordSize() + text.getChangeY()));
            }
            g.dispose();
            //输出图片
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 保存文件
            ImageIO.write(bi, "PNG", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "水印添加失败：" + e.getMessage());
        }
    }

    /**
     * 给图片不同位置添加多个图片水印、可设置水印图片旋转角度
     *
     * @param icon   水印图片路径（如：F:/images/icon.png）
     * @param source 没有加水印的图片路径（如：F:/images/6.jpg）
     * @param mark   水印参数
     */
    public static byte[] markImageByMoreIcon(InputStream icon, InputStream source, Mark mark) {
        try {
            //将icon加载到内存中
            Image ic = ImageIO.read(icon);
            //icon高度
            int icheight = ic.getHeight(null);
            //将源图片读到内存中
            Image img = ImageIO.read(source);
            //图片宽
            int width = img.getWidth(null);
            //图片高
            int height = img.getHeight(null);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //创建一个指定 BufferedImage 的 Graphics2D 对象
            Graphics2D g = bi.createGraphics();
            //x,y轴默认是从0坐标开始
            int x = 0;
            int y = 0;
            //默认两张水印图片的间隔高度是水印图片的1/3
            int temp = icheight / 3;
            int space = 1;
            if (height >= icheight) {
                space = height / icheight;
                if (space >= 2) {
                    temp = y = icheight / 2;
                    if (space == 1 || space == 0) {
                        x = 0;
                        y = 0;
                    }
                }
            } else {
                x = 0;
                y = 0;
            }
            //设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            //呈现一个图像，在绘制前进行从图像空间到用户空间的转换
            g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            if (null != mark.getDegree()) {
                //设置水印旋转
                g.rotate(Math.toRadians(mark.getDegree()), (double) bi.getWidth() / 2, (double) bi.getHeight() / 2);
            }
            for (int i = 0; i < space; i++) {
                //透明度，最小值为0，最大值为1
                float clarity = 0.6f;
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, clarity));
                //表示水印图片的坐标位置(x,y)
                g.drawImage(ic, x, y, null);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
                y += (icheight + temp);
            }
            g.dispose();
            //输出图片
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 保存文件
            ImageIO.write(bi, "PNG", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "水印添加失败：" + e.getMessage());
        }
    }

    /**
     * 给图片添加单个图片水印、可设置水印图片旋转角度
     *
     * @param icon   水印图片路径（如：F:/images/icon.png）
     * @param source 没有加水印的图片路径（如：F:/images/6.jpg）
     * @param mark   水印参数
     */
    public static byte[] markImageBySingleIcon(InputStream icon, InputStream source, Mark mark) {
        try {
            //将icon加载到内存中
            Image ic = ImageIO.read(icon);
            //icon高度
            int icheight = ic.getHeight(null);
            //将源图片读到内存中
            Image img = ImageIO.read(source);
            //图片宽
            int width = img.getWidth(null);
            //图片高
            int height = img.getHeight(null);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //创建一个指定 BufferedImage 的 Graphics2D 对象
            Graphics2D g = bi.createGraphics();
            //x,y轴默认是从0坐标开始
            int x = 0;
            int y = (height / 2) - (icheight / 2);
            //设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            //呈现一个图像，在绘制前进行从图像空间到用户空间的转换
            g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            if (null != mark.getDegree()) {
                //设置水印旋转
                g.rotate(Math.toRadians(mark.getDegree()), (double) bi.getWidth() / 2, (double) bi.getHeight() / 2);
            }
            //透明度，最小值为0，最大值为1
            float clarity = 0.6f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, clarity));
            //表示水印图片的坐标位置(x,y)
            g.drawImage(ic, x, y, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.dispose();
            //输出图片
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 保存文件
            ImageIO.write(bi, "PNG", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "水印添加失败：" + e.getMessage());
        }
    }

    /**
     * 给图片加马赛克
     *
     * @param source    原图片路径（如：F:/images/6.jpg）
     * @param output    打马赛克后，图片保存的路径（如：F:/images/）
     * @param imageName 图片名称（如：11111）
     * @param imageType 图片类型（如：jpg）
     * @param size      马赛克尺寸，即每个矩形的宽高
     * @return
     */
    public static String markImageByMosaic(String source, String output, String imageName, String imageType, int size) {
        try {
            File file = new File(source);
            if (!file.isFile()) {
                return file + " 不是一个图片文件!";
            }
            BufferedImage img = ImageIO.read(file); // 读取该图片
            int width = img.getWidth(null); //原图片宽
            int height = img.getHeight(null); //原图片高
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //马赛克格尺寸太大或太小
            if (width < size || height < size) {
                return "马赛克格尺寸太大";
            }
            if (size <= 0) {
                return "马赛克格尺寸太小";
            }
            int xcount = 0; //x方向绘制个数
            int ycount = 0; //y方向绘制个数
            if (width % size == 0) {
                xcount = width / size;
            } else {
                xcount = width / size + 1;
            }
            if (height % size == 0) {
                ycount = height / size;
            } else {
                ycount = height / size + 1;
            }
            int x = 0; //x坐标
            int y = 0;
            //y坐标
            //绘制马赛克(绘制矩形并填充颜色)
            Graphics2D g = bi.createGraphics();
            for (int i = 0; i < xcount; i++) {
                for (int j = 0; j < ycount; j++) {
                    //马赛克矩形格大小
                    int mwidth = size;
                    int mheight = size;
                    if (i == xcount - 1) {  //横向最后一个不够一个size
                        mwidth = width - x;
                    }
                    if (j == ycount - 1) { //纵向最后一个不够一个size
                        mheight = height - y;
                    }
                    //矩形颜色取中心像素点RGB值
                    int centerX = x;
                    int centerY = y;
                    if (mwidth % 2 == 0) {
                        centerX += mwidth / 2;
                    } else {
                        centerX += (mwidth - 1) / 2;
                    }
                    if (mheight % 2 == 0) {
                        centerY += mheight / 2;
                    } else {
                        centerY += (mheight - 1) / 2;
                    }
                    Color color = new Color(img.getRGB(centerX, centerY));
                    g.setColor(color);
                    g.fillRect(x, y, mwidth, mheight);
                    y = y + size;// 计算下一个矩形的y坐标
                }
                y = 0;// 还原y坐标
                x = x + size;// 计算x坐标
            }
            g.dispose();
            File sf = new File(output, imageName + "." + imageType);
            if (!sf.exists()) {
                //不存在时创建文件
                sf.getParentFile().mkdirs();
            }
            // 保存图片
            ImageIO.write(bi, imageType, sf);
            return sf.getAbsolutePath();
        } catch (Exception e) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "水印添加失败：" + e.getMessage());
        }
    }
}
