package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.po.Category;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.CategoryService
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:25
 * @Version: 1.0
 */
public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(Long eid);
}
