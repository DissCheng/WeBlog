package com.disscheng.weblog.controller.users;

import com.disscheng.weblog.dto.ArticleDTO;
import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.dto.ArticleUpdateDTO;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.ArticleService;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import com.disscheng.weblog.vo.ArticleVO;
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
     * @param articleDTO 文章
     * @return 新增结果
     */
    @PostMapping("/publish")
    public Result<Void> publish(@RequestBody ArticleDTO articleDTO) {
        log.info("publish article: {}", articleDTO);
        articleService.publish(articleDTO);
        return Result.success(null);
    }

    /**
     * 获取文章详情
     * @param id 文章id
     * @return 文章详情
     */
    @GetMapping("/detail/{id}")
    public Result<ArticleVO> detail(@PathVariable("id") long id) {
        log.info("get article id: {}", id);
        ArticleVO articleVO = articleService.getArticleDetail(id);
        return Result.success(articleVO);
    }

    /**
     * 更新文章
     * @param articleUpdateDTO 文章
     * @return 更新结果
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody ArticleUpdateDTO articleUpdateDTO) {
        log.info("update article: {}", articleUpdateDTO);
        articleService.update(articleUpdateDTO);
        return Result.success(null);
    }
}
