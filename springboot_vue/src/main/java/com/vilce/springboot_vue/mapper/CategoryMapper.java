package com.vilce.springboot_vue.mapper;

import com.vilce.springboot_vue.model.po.Category;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 图书分类相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.mapper.CategoryMapper
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:30
 * @Version: 1.0
 */
@Component
public interface CategoryMapper {

    /**
     * 获取所有分类
     * @return
     */
    List<Category> getAllCategory();

    /**
     * 根据id获取分类
     * @param id
     * @return
     */
    Category getCategoryById(int id);
}
