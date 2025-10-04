package com.disscheng.weblog.mapper;

import com.disscheng.weblog.dto.ArticleArchivePageQueryDTO;
import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.dto.ArticleStatisticDTO;
import com.disscheng.weblog.entity.Article;

import com.disscheng.weblog.entity.ArticleCategory;
import com.disscheng.weblog.entity.ArticleContent;
import com.disscheng.weblog.entity.ArticleTag;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import com.disscheng.weblog.vo.ArticlePublishStatisticsVO;
import com.github.pagehelper.Page;
import io.minio.ObjectWriteArgs;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Mapper
public interface ArticleMapper {
    /*
     * 获取文章总数
     * @return 文章总数
     */
    @Select("select count(*) from t_article")
    public int count();

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
     public Long getArticleCategory(long articleId);

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
    /**
     * 获取下一篇文章
     * @param id
     * @return
     */
     @Select("select * from t_article where id > #{id} order by id limit 1")
     public Article getNextArticle(long id);


     /**
      * 获取上一篇文章
      * @param id
      * @return
      */
     @Select("select * from t_article where id < #{id} order by id desc limit 1")
     public Article getPreArticle(long id);

    /**
     * 阅读量更新
     * @param id
     */
     @Update("update t_article set read_num = read_num + 1 where id = #{id}")
     public void updateReadNum(long id);

    /**
     * 阅读量更新
     * @param id
     */
    @Update("update t_article set read_num = read_num + #{add} where id = #{id}")
    public void batchUpdateReadNum(long id,long add);


    /**
     * 获取文章总访问量
     * @return
     */
     @Select("select sum(read_num) from t_article")
     public Long getTotalReadNum();

    /**
     * 根据日期筛选文章
     * @param startDate
     * @param endDate
     * @return
     */
     @Select("select Date(create_time),count(*) as count from t_article where create_time >= #{startDate} and create_time <= #{endDate}" +
             " group by Date(create_time) order by Date(create_time)")
     public List<ArticleStatisticDTO> getArticleByDate(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * 插入次日文章访问量
     */
     public int insertArticlePvStatistics();

    /**
     * 更新当日文章浏览量
     *
     */
    @Update("update t_statistics_article_pv set pv_count = pv_count + 1 where pv_date = curdate()")
     public int updatePV();

    /**
     * 获取当日PV
     * @return
     */
    @Select("select pv_count from t_statistics_article_pv where pv_date = ${date}")
     public Long getPV(LocalDate date);
}

