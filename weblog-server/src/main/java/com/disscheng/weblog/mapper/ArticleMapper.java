package com.disscheng.weblog.mapper;

import com.disscheng.weblog.dto.ArticleArchivePageQueryDTO;
import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.entity.Article;

import com.disscheng.weblog.entity.ArticleCategory;
import com.disscheng.weblog.entity.ArticleContent;
import com.disscheng.weblog.entity.ArticleTag;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import com.github.pagehelper.Page;
import io.minio.ObjectWriteArgs;
import org.apache.ibatis.annotations.*;

import java.util.List;


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


    /**
     * 插入文章
     * @param article 文章对象
     */
    @Insert("insert into t_article(id,title, cover, summary, create_time, update_time, is_deleted, read_num) " +
            "values(0,#{title}, #{cover}, #{summary}, #{createTime}, #{updateTime}, #{isDeleted}, #{readNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(Article article);

    /**
     * 插入文章内容
     * @param articleContent 文章内容对象
     */
     @Insert("insert into t_article_content(id,article_id, content) values(0,#{articleId}, #{content})")
     public int insertContent(ArticleContent articleContent);

     /**
      * 插入文章分类
      *
      */
     @Insert("insert into t_article_category_rel(id, article_id, category_id) values(0,#{articleId}, #{categoryId})")
     public int insertCategory(ArticleCategory  articleCategory);

     /**
      * 插入文章标签
      *
      */
     @Insert("insert into t_article_tag_rel(id,article_id, tag_id) values(0,#{articleId}, #{tagId})")
     public int insertTag(ArticleTag articleTag);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @Select("select * from t_article where id = #{id}")
     public Article getArticle(long id);

     /**
      * 获取文章内容
      * @param articleId
      * @return
      */
     @Select("select content from t_article_content where article_id = #{articleId}")
     public String getArticleContent(long articleId);

     /**
      * 获取文章分类
      * @param articleId
      * @return
      */
     @Select("select category_id from t_article_category_rel where article_id = #{articleId}")
     public long getArticleCategory(long articleId);

     /**
      * 获取文章标签
      * @param articleId
      * @return
      */
     @Select("select tag_id from t_article_tag_rel where article_id = #{articleId}")
     public List<Long> getArticleTag(long articleId);
     /**
      * 删除文章内容
      * @param id
      */
     @Delete("delete from t_article_content where article_id = #{id}")
     public void deleteContent(long id);
     /**
      * 删除文章分类
      * @param id
      */
     @Delete("delete from t_article_category_rel where article_id = #{id}")
     public void deleteCategory(long id);
     /**
      * 删除文章标签
      * @param id
      */
     @Delete("delete from t_article_tag_rel where article_id = #{id}")
     public void deleteTag(long id);

}
