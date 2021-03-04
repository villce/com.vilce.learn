package com.vilce.springboot_vue.module.secret.service.impl;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.springboot_vue.module.secret.mapper.SecretMapper;
import com.vilce.springboot_vue.module.secret.model.Modules;
import com.vilce.springboot_vue.module.secret.model.ModulesRes;
import com.vilce.springboot_vue.module.secret.service.SecretService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
        Modules modules = new Modules(modulesDate, modulesTitle);
        // 插入图片模块表
        secretMapper.createModules(modules);
        // 循环插入图片地址
        imgUrlList.forEach(imgUrl -> {
            CompletableFuture imgFuture = CompletableFuture.runAsync(()-> {
                secretMapper.insertImg(modules.getId(), imgUrl);
            });
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
        Modules modules = new Modules(id, modulesDate, modulesTitle);
        // 更新图片模块表
        secretMapper.updateModules(modules);
        // 删除该模块所有图片
        secretMapper.deleteImg(modules.getId());
        // 循环插入图片地址
        imgUrlList.forEach(imgUrl -> {
            CompletableFuture imgFuture = CompletableFuture.runAsync(()-> {
                secretMapper.insertImg(modules.getId(), imgUrl);
            });
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
        // 删除该模块所有图片
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
}
