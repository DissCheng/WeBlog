package com.disscheng.weblog.mapper;

import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.entity.Article;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ArticleMapper {

    /*
     * 分页查询文章列表
     * @param articlePageQueryDTO 文章分页查询DTO
     * @return 文章分页查询结果
     */
    public Page<Article> pageQuery(ArticlePageQueryDTO articlePageQueryDTO);

    /**
     * 删除文章
     * @param id 文章id
     * @return 影响行数
     */
    @Delete("delete from t_article where id = #{id}")
    public int delete(Long id);
}
