package com.disscheng.weblog.service;

import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.vo.ArticlePageQueryVO;

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
}
