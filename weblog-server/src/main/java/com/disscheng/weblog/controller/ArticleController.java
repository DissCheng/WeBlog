package com.disscheng.weblog.controller;

import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.ArticleService;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/article")
@Slf4j
public class ArticleController{
    @Autowired
    private ArticleService articleService;

    /**
     * 分页查询文章列表
     * @param articlePageQueryDTO 文章分页查询条件
     * @return 文章列表
     */
    @PostMapping("/list")
    public Result<ArticlePageQueryVO> list(@RequestBody ArticlePageQueryDTO articlePageQueryDTO) {
        log.info("articlePageQueryDTO: {}", articlePageQueryDTO);
        ArticlePageQueryVO articlePageQueryVO = articleService.list(articlePageQueryDTO);
        return Result.success(articlePageQueryVO);
    }
    /**
     * 删除文章
     * @param id 文章id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable("id") long id) {
        log.info("delete article id: {}", id);
        articleService.delete(id);
        return Result.success(null);
    }
    /**
     * 新增文章
     * @param article 文章
     * @return 新增结果
     */

}
