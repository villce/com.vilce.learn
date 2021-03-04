package com.vilce.springboot_vue.module.secret.mapper;

import com.vilce.springboot_vue.module.secret.model.Modules;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.secret.mapper
 * @Author: 雷才哲
 * @Date: 2021/3/2 下午5:44
 * @Version: 1.0
 */
@Mapper
public interface SecretMapper {

    /**
     * 创建图片模块
     *
     * @param modules 图片模块
     */
    void createModules(Modules modules);

    /**
     * 插入模块图片
     *
     * @param id     模块ID
     * @param imgUrl 图片地址
     */
    void insertImg(int id, String imgUrl);

    /**
     * 更新图片模块
     *
     * @param modules
     */
    void updateModules(Modules modules);

    /**
     * 删除图片模块下所有图片
     *
     * @param id
     */
    void deleteImg(int id);

    /**
     * 删除图片模块
     *
     * @param modulesId
     */
    void deleteModules(int modulesId);

    /**
     * 分页获取图片模块
     *
     * @param start 起始
     * @param num   获取量
     * @return
     */
    List<Modules> getModules(int start, int num);

    /**
     * 获取模块图片
     *
     * @param id 模块ID
     * @return
     */
    List<String> getModulesImg(int id);

    /**
     * 统计模块数量
     *
     * @return
     */
    int countModules();
}
