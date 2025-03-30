package com.disscheng.weblog.service.impl;

import com.disscheng.weblog.dto.CategoryDTO;
import com.disscheng.weblog.dto.CategoryPageQueryDTO;
import com.disscheng.weblog.entity.Category;
import com.disscheng.weblog.mapper.CategoryMapper;
import com.disscheng.weblog.service.CategoryService;
import com.disscheng.weblog.vo.CategoryPageQueryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页查询分类列表
     * @param categoryPageQueryDTO
     * @return
     */
    public CategoryPageQueryVO list(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getCurrent(), categoryPageQueryDTO.getSize());
        Page<Category> categoryPage = categoryMapper.pageQuery(categoryPageQueryDTO);
        CategoryPageQueryVO categoryPageQueryVO = new CategoryPageQueryVO();
        categoryPageQueryVO.setTotal(categoryPage.getTotal());
        categoryPageQueryVO.setCurrent(categoryPageQueryDTO.getCurrent());
        categoryPageQueryVO.setSize(categoryPage.getPageSize());
        categoryPageQueryVO.setPages(categoryPage.getPages());
        categoryPageQueryVO.setData(categoryPage.getResult());
        return categoryPageQueryVO;
    }
    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    public Category add(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setDeleted(false);
        categoryMapper.add(category);
        return category;
    }
    /**
     * 删除分类
     * @param id
     */
    public void delete(Long id) {
        categoryMapper.delete(id);
    }
}
