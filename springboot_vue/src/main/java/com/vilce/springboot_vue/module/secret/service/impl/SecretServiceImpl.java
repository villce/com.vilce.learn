package com.vilce.springboot_vue.module.secret.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vilce.common.model.enums.DateEnum;
import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.utils.FileUtils;
import com.vilce.common.utils.JSONUtils;
import com.vilce.common.utils.TimeUtils;
import com.vilce.springboot_vue.module.secret.mapper.SecretMapper;
import com.vilce.springboot_vue.module.secret.model.Modules;
import com.vilce.springboot_vue.module.secret.model.ModulesRes;
import com.vilce.springboot_vue.module.secret.service.SecretService;
import com.vilce.springboot_vue.module.tool.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.secret.service.impl
 * @Author: 雷才哲
 * @Date: 2021/3/2 下午5:40
 * @Version: 1.0
 */
@Service
public class SecretServiceImpl implements SecretService {

    @Autowired
    private SecretMapper secretMapper;
    @Value("${covers.url}")
    private String coversUrl;
    @Value("${com.vilce.image.url}")
    private String imageUrl;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String Secret_Module = "com:vilce:secret:modules";

    /**
     * 创建图片模块
     *
     * @param modulesDate  模块日期
     * @param modulesTitle 模块标题
     * @param imgList      图片名称
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createModules(String modulesDate, String modulesTitle, List<String> imgList) {
        if (StringUtils.isBlank(modulesDate)) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "模块时间不能为空");
        }
        if (StringUtils.isBlank(modulesTitle)) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "模块标题不能为空");
        }
        Modules modules = new Modules(modulesDate, modulesTitle);
        // 插入图片模块表
        secretMapper.createModules(modules);
        imgList.forEach(image -> {
            // 插入图片地址
            secretMapper.insertImg(modules.getId(), image);
        });
    }

    /**
     * 更新图片模块
     *
     * @param id           系统物理主键
     * @param modulesDate  模块日期
     * @param modulesTitle 模块标题
     * @param imgList      图片地址
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateModules(int id, String modulesDate, String modulesTitle, List<String> imgList) {
        if (StringUtils.isBlank(modulesDate)) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "模块时间不能为空");
        }
        if (StringUtils.isBlank(modulesTitle)) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "模块标题不能为空");
        }
        Modules modulesDto = new Modules(id, modulesDate, modulesTitle);
        // 更新图片模块表
        secretMapper.updateModules(modulesDto);
        // 删除该模块所有图片
        secretMapper.deleteImg(modulesDto.getId());
        imgList.forEach(imgUrl -> {
            // 插入图片地址
            secretMapper.insertImg(modulesDto.getId(), imgUrl);
        });
    }

    /**
     * 删除图片模块
     *
     * @param modulesId 图片模块ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteModules(int modulesId) {
        // 删除图片模块
        secretMapper.deleteModules(modulesId);
        // 删除该模块所有图片地址
        secretMapper.deleteImg(modulesId);
    }

    /**
     * 分页获取图片模块
     *
     * @param pageIndex 页码
     * @param pageSize  页面数量
     * @return
     */
    @Override
    public ModulesRes getModules(int pageIndex, int pageSize) {
        ModulesRes modulesRes = new ModulesRes();
        List<Modules> modulesList = secretMapper.getModules((pageIndex - 1) * pageSize, pageSize);
        modulesList.forEach(modules -> {
            List<String> imgUrlList = secretMapper.getModulesImg(modules.getId());
            modules.setImgUrlList(imgUrlList);
        });
        modulesRes.setModulesList(modulesList);
        modulesRes.setNum(secretMapper.countModules());
        return modulesRes;
    }

    /**
     * 根据ID查询模块
     *
     * @param modulesId 模块ID
     * @return
     */
    @Override
    public Modules findModules(int modulesId) {
        Modules modules = secretMapper.findModulesById(modulesId);
        List<String> imgUrlList = secretMapper.getModulesImg(modules.getId());
        modules.setImgUrlList(imgUrlList);
        return modules;
    }

    /**
     * 获取最新模块
     *
     * @return
     */
    @Override
    public Modules getNewModules() {
        List<Modules> modulesList = null;
        String redisStr = redisTemplate.opsForValue().get(Secret_Module);
        if (StringUtils.isNotBlank(redisStr)) {
            modulesList = JSONUtils.toJavaBean(redisStr, List.class, Modules.class);
        } else {
            modulesList = secretMapper.getAllModules();
        }
        if (!CollectionUtils.isEmpty(modulesList)) {
            Modules modules = modulesList.get(0);
            modules.setIndex(0);
            List<String> imgUrlList = secretMapper.getModulesImg(modules.getId());
            modules.setImgUrlList(imgUrlList);
            return modules;
        }
        return null;
    }

    /**
     * 时间轴分段获取模块
     *
     * @param index 索引
     * @param next 相邻值
     * @return
     */
    @Override
    public Modules timeLineGetModules(int index, int next) {
        List<Modules> modulesList = null;
        String redisStr = redisTemplate.opsForValue().get(Secret_Module);
        if (StringUtils.isNotBlank(redisStr)) {
            modulesList = JSONUtils.toJavaBean(redisStr, List.class, Modules.class);
        } else {
            modulesList = secretMapper.getAllModules();
        }
        if (!CollectionUtils.isEmpty(modulesList)) {
            Modules modules = modulesList.get(index + next);
            modules.setIndex(index + next);
            List<String> imgUrlList = secretMapper.getModulesImg(modules.getId());
            modules.setImgUrlList(imgUrlList);
            return modules;
        }
        return null;
    }

    /**
     * 上传压缩图片
     *
     * @param multipartFile 图片
     * @return
     */
    @Override
    public String coversImage(MultipartFile multipartFile) {
        // 获取文件名
        String imageName = multipartFile.getOriginalFilename();
        String dateStr = DateFormatUtils.format(new Date(), DateEnum.YYYY_MM.getFormat());
        String compressPath = StringUtils.join(coversUrl, dateStr);
        File compressPathDir = new File(compressPath);
        if (!compressPathDir.exists()) {
            compressPathDir.mkdirs();
        }
        String sourcePath = StringUtils.join(compressPath, "/source");
        File sourcePathDir = new File(sourcePath);
        if (!sourcePathDir.exists()) {
            sourcePathDir.mkdirs();
        }
        File file = new File(sourcePath + "/" + imageName);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            long fileSize = FileUtils.sizeOf(file);
            if (fileSize >= 1024 * 1024) {
                // 图片大于1M时进行压缩
                Thumbnails.of(file)
                        //图片大小（长宽）压缩比例 从0-1，1表示原图
                        .scale(1f)
                        //图片质量压缩比例 从0-1，越接近1质量越好
                        .outputQuality(0.4f)
                        .toOutputStream(new FileOutputStream(StringUtils.join(compressPath, "/", imageName)));
            } else {
                // 图片大于1M时进行压缩
                Thumbnails.of(file)
                        //图片大小（长宽）压缩比例 从0-1，1表示原图
                        .scale(1f)
                        //图片质量压缩比例 从0-1，越接近1质量越好
                        .outputQuality(1f)
                        .toOutputStream(new FileOutputStream(StringUtils.join(compressPath, "/", imageName)));
            }
            return StringUtils.join(imageUrl, dateStr, "/", imageName);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.DATA_EXCEPTION.getStatus(), "图片异常");
        }
    }
}
