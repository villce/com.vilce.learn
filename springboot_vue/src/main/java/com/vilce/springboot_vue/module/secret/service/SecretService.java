package com.vilce.springboot_vue.module.secret.service;

import com.vilce.springboot_vue.module.secret.model.ModulesRes;

import java.util.List;

/**
 * @Description: 秘密花园服务
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.secret.service
 * @Author: 雷才哲
 * @Date: 2021/3/2 下午5:31
 * @Version: 1.0
 */
public interface SecretService {

    /**
     * 创建图片模块
     *
     * @param modulesDate  模块日期
     * @param modulesTitle 模块标题
     * @param imgUrlList   图片地址
     */
    void createModules(String modulesDate, String modulesTitle, List<String> imgUrlList);

    /**
     * 更新图片模块
     *
     * @param id           系统物理主键
     * @param modulesDate  模块日期
     * @param modulesTitle 模块标题
     * @param imgUrlList   图片地址
     */
    void updateModules(int id, String modulesDate, String modulesTitle, List<String> imgUrlList);

    /**
     * 删除图片模块
     *
     * @param modulesId 图片模块ID
     */
    void deleteModules(int modulesId);

    /**
     * 分页获取图片模块
     *
     * @param pageIndex 页码
     * @param pageSize  页面数量
     * @return
     */
    ModulesRes getModules(int pageIndex, int pageSize);
}
