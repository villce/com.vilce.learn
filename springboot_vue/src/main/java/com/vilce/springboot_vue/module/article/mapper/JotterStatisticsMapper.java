package com.vilce.springboot_vue.module.article.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.mapper.JotterStatisticsMapper
 * @Author: 雷才哲
 * @Date: 2020/11/18 14:08
 * @Version: 1.0
 */
@Mapper
public interface JotterStatisticsMapper {

    /**
     * 获取所有的文章标签
     *
     * @return
     */
    List<String> getAllLabels();
}
