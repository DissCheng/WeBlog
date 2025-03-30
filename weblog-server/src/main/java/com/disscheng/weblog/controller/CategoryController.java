package com.disscheng.weblog.controller;


import com.disscheng.weblog.dto.CategoryDTO;
import com.disscheng.weblog.dto.CategoryPageQueryDTO;
import com.disscheng.weblog.entity.Category;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.CategoryService;
import com.disscheng.weblog.vo.CategoryPageQueryVO;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 分页查询分类列表
     * @param categoryPageQueryDTO
     * @return
     */
    @PostMapping("/list")
    public Result<CategoryPageQueryVO> list(@RequestBody CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("categoryPageQueryDTO: {}", categoryPageQueryDTO);
        return Result.success(categoryService.list(categoryPageQueryDTO));
    }
    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("/listAll")
    public Result<List<Category>> listAll() {
        return Result.success(categoryService.listAll());
    }
    /**
     * 添加分类
     * @param categoryDTO
     * @return
     */
    @PostMapping("/add")
    public Result<String> add(@RequestBody CategoryDTO categoryDTO) {
        log.info("categoryDTO: {}", categoryDTO);
        categoryService.add(categoryDTO);
        return Result.success();
    }
    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable("id") String id) {
        categoryService.delete(Long.parseLong(id));
        log.info("删除分类id: {}", id);
        return Result.success("删除成功");
    }
}
