package com.disscheng.weblog.controller.visitors;


import com.disscheng.weblog.dto.ArticlePageQueryByCategoryDTO;
import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.CategoryService;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/category")
@Slf4j
public class FrontendCategoryController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 根据分类查询文章列表
     * @param articlePageQueryByCategoryDTO 文章查询条件
     * @return 文章列表
     */
    @PostMapping("/article/list")
    public Result<ArticlePageQueryVO> getArticlesByCategory(@RequestBody ArticlePageQueryByCategoryDTO articlePageQueryByCategoryDTO){
        log.info("getArticlesByCategory: articlePageQueryByCategoryDTO={}", articlePageQueryByCategoryDTO);
        return Result.success(categoryService.getArticlesByCategory(articlePageQueryByCategoryDTO));
    }
}
