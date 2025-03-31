package com.disscheng.weblog.service;

import com.disscheng.weblog.dto.*;
import com.disscheng.weblog.vo.ArticleArchivePageQueryVO;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import com.disscheng.weblog.vo.ArticleVO;

import java.util.List;

public interface ArticleService {

    /**
     * 分页查询文章列表
     * @param articlePageQueryDTO 文章分页查询条件
     * @return 文章分页查询结果
     */
    public ArticlePageQueryVO list(ArticlePageQueryDTO articlePageQueryDTO);

    /**
     * 删除文章
     * @param id 文章id
     * @return 是否删除成功
     */
    public void delete(long id);


    /**
     * 发布文章
     * @param articleDTO 文章
     * @return 发布结果
     */
    public int publish(ArticleDTO articleDTO);

    /**
     * 获取文章详情
     * @param id 文章id
     * @return 文章详情
     */
    public ArticleVO getArticleDetail(long id);

    /**
     * 更新文章
     * @param articleUpdateDTO 文章
     */
    public void update(ArticleUpdateDTO articleUpdateDTO);

    /**
     * 前台分页查询文章列表
     * @param articlePageQueryDTO 文章分页查询条件
     * @return 文章分页查询结果
     *
     */
     public TagDTO.ArticleFrontendPageQueryVO listFrontend(ArticlePageQueryDTO articlePageQueryDTO);


     /**
      * 文章归档
      * @return 文章列表
      */
     public ArticleArchivePageQueryVO archiveList(ArticlePageQueryDTO articlePageQueryDTO);
}
