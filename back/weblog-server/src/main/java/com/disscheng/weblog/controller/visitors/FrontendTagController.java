package com.disscheng.weblog.controller.visitors;

import com.disscheng.weblog.dto.ArticlePageQueryByCategoryDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.mapper.ArticleMapper;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.TagService;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@Slf4j
public class FrontendTagController {

    /**
     * 根据标签名称获取文章列表
     * @param tagName 标签名称
     * @return 文章列表
     */
    @Autowired
    private TagService tagService;


    @PostMapping("/article/list")
    public Result<ArticlePageQueryVO> getArticlesByTag(@RequestBody ArticlePageQueryByCategoryDTO articlePageQueryByCategoryDTO){
        log.info("getArticlesByTag: articlePageQueryByCategoryDTO={}", articlePageQueryByCategoryDTO);
        return Result.success(tagService.getArticlesByTag(articlePageQueryByCategoryDTO));
    }
}
