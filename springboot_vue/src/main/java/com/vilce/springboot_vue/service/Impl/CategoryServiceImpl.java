package com.vilce.springboot_vue.service.Impl;

import com.vilce.springboot_vue.dao.CategoryMapper;
import com.vilce.springboot_vue.model.po.Category;
import com.vilce.springboot_vue.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.CategoryServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:30
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public Category getCategoryById(Long eid) {
        return categoryMapper.getCategoryById(eid);
    }
}
