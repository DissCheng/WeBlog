package com.disscheng.weblog.service;

import com.disscheng.weblog.dto.CategoryDTO;
import com.disscheng.weblog.dto.CategoryPageQueryDTO;
import com.disscheng.weblog.entity.Category;
import com.disscheng.weblog.vo.CategoryPageQueryVO;

import java.util.List;

public interface CategoryService {

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO 分页查询条件
     * @return 分类列表
     */
    public CategoryPageQueryVO list(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 获取所有分类
     * @return 分类列表
     */
    public List<Category> listAll();
    /**
     * 新增分类
     *
     * @param categoryDTO 分类
     * @return 分类
     */
    public Category add(CategoryDTO categoryDTO);

    /**
     * 删除分类
     *
     */
    public void delete(Long id);


}