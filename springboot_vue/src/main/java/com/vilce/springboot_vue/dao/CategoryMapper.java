package com.vilce.springboot_vue.dao;

import com.vilce.springboot_vue.model.po.Category;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.dao.CategoryMapper
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:30
 * @Version: 1.0
 */
@Component
public interface CategoryMapper {
    List<Category> getAllCategory();
    Category getCategoryById(Long eid);
}
