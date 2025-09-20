package com.disscheng.weblog.controller.visitors;

import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.dto.TagDTO;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.ArticleService;
import com.disscheng.weblog.vo.ArticleFrontendDetailVO;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.disscheng.weblog.vo.ArticleArchivePageQueryVO;

import java.util.Map;

@RestController
@RequestMapping("/article")
@Slf4j
public class FrontendArticleController {

    @Autowired
    private ArticleService articleService;
    /**
     * 前台文章列表
     * @param articlePageQueryDTO
     * @return
     */
    @PostMapping("/list")
    public Result<TagDTO.ArticleFrontendPageQueryVO> list(@RequestBody ArticlePageQueryDTO  articlePageQueryDTO) {
        return Result.success(articleService.listFrontend(articlePageQueryDTO));
    }
    /**
     * 前台文章按月份归档
     * @param
     * @return
     */
    @PostMapping("/archive/list")
    public Result<ArticleArchivePageQueryVO> achieveList(@RequestBody ArticlePageQueryDTO articlePageQueryDTO) {
        log.info(articlePageQueryDTO.toString());
        return Result.success(articleService.archiveList(articlePageQueryDTO));
    }

    /**
     * 前台获取文章详情
     *
     */
    @PostMapping("/detail")
    public Result<ArticleFrontendDetailVO>  detail(@RequestBody Map<String,Object> rq) {
        log.info(rq.toString());
        long articleId = Long.parseLong(rq.get("articleId").toString());
        return Result.success(articleService.getArticleFrontendDetail(articleId));
    }

    /**
     * 文章阅读数更新
     *
     */
    @PostMapping("/updateReadNum")
    public Result<String> updateReadNum(@RequestBody Map<String,Object> rq) {
        log.info(rq.toString());
        articleService.updateReadNum(Long.parseLong(rq.get("articleId").toString()));
        return Result.success();
    }
}
