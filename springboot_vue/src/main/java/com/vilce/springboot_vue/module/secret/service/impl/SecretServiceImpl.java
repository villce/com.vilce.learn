package com.vilce.springboot_vue.module.secret.service.impl;

import com.vilce.common.model.enums.DateEnum;
import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.utils.TimeUtils;
import com.vilce.springboot_vue.module.secret.mapper.SecretMapper;
import com.vilce.springboot_vue.module.secret.model.Modules;
import com.vilce.springboot_vue.module.secret.model.ModulesRes;
import com.vilce.springboot_vue.module.secret.service.SecretService;
import com.vilce.springboot_vue.module.tool.service.ImageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    /**
     * 创建图片模块
     *
     * @param modulesDate  模块日期
     * @param modulesTitle 模块标题
     * @param imgUrlList   图片地址
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createModules(String modulesDate, String modulesTitle, List<String> imgUrlList) {
        if (StringUtils.isBlank(modulesDate)) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "模块时间不能为空");
        }
        if (StringUtils.isBlank(modulesTitle)) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "模块标题不能为空");
        }
        Modules modules = new Modules(modulesDate, modulesTitle);
        // 插入图片模块表
        secretMapper.createModules(modules);
        // 循环插入图片地址
        imgUrlList.forEach(imgUrl -> {
            secretMapper.insertImg(modules.getId(), imgUrl);
        });
    }

    /**
     * 更新图片模块
     *
     * @param id           系统物理主键
     * @param modulesDate  模块日期
     * @param modulesTitle 模块标题
     * @param imgUrlList   图片地址
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateModules(int id, String modulesDate, String modulesTitle, List<String> imgUrlList) {
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
        // 循环插入图片地址
        imgUrlList.forEach(imgUrl -> {
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
    public ModulesRes getNewModules(int pageSize) {
        ModulesRes modulesRes = new ModulesRes();
        List<Modules> modulesList = secretMapper.getNewModules(pageSize);
        modulesList.forEach(modules -> {
            List<String> imgUrlList = secretMapper.getModulesImg(modules.getId());
            modules.setImgUrlList(imgUrlList);
        });
        Collections.sort(modulesList);
        modulesRes.setModulesList(modulesList);
        modulesRes.setNum(secretMapper.countModules());
        return modulesRes;
    }

    /**
     * 时间轴分段获取模块
     *
     * @param pageIndex 当前段
     * @param pageSize  页面展示数量
     * @return
     */
    @Override
    public ModulesRes timeLineGetModules(int pageIndex, int pageSize) {
        ModulesRes modulesRes = new ModulesRes();
        List<Modules> modulesList = secretMapper.getModules((pageIndex - 1) * pageSize, pageSize);
        // 判断是否为末端
        if (modulesList.size() != pageSize) {
            modulesList = secretMapper.getModulesASC(pageSize);
        } else {
            Collections.sort(modulesList);
        }
        modulesList.forEach(modules -> {
            List<String> imgUrlList = secretMapper.getModulesImg(modules.getId());
            modules.setImgUrlList(imgUrlList);
        });
        modulesRes.setModulesList(modulesList);
        modulesRes.setNum(secretMapper.countModules());
        return modulesRes;
    }

    /**
     * 上传图片
     *
     * @param file 图片
     * @return
     */
    @Override
    public String coversUpload(MultipartFile file) {
        // 获取图片名称
        String date = DateFormatUtils.format(TimeUtils.formatDate(file.getOriginalFilename().substring(0, 6),
                DateEnum.YYYYMM.getFormat()), DateEnum.YYYY_MM.getFormat());
        File imageFolder = new File(StringUtils.join(coversUrl, date));
        File f = new File(imageFolder, file.getOriginalFilename());
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURL = StringUtils.join(imageUrl, date, "/", f.getName());
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
