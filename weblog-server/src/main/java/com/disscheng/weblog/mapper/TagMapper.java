package com.disscheng.weblog.mapper;

import com.disscheng.weblog.dto.TagPageQueryDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.entity.Tag;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {

    /**
     * 分页查询标签列表
     * @return 标签列表
     */
    public Page<Tag> pageQuery(TagPageQueryDTO tagPageQueryDTO);

    /**
     * 添加标签
     * @param tag 标签
     * @return 影响行数
     */
    @Insert("insert into t_tag(name, create_time, update_time, is_deleted)" +
            " values(#{name}, #{createTime}, #{updateTime}, #{isDeleted})")
    public int add(Tag tag);

    /**
     * 删除标签
     * @param id 标签id
     * @return 影响行数
     */
    @Delete("delete from t_tag where id = #{id}")
    public int delete(long id);

    /**
     * 根据名称查询标签
     * @param name 标签名称
     * @return 标签列表
     */
    @Select("select * from t_tag where name like concat('%', #{name}, '%')")
    List<Tag> selectByName(String name);

    /**
     * 根据id查询标签
     * @param id 标签id
     * @return 标签
     */
    @Select("select * from t_tag where id = #{id}")
    Tag selectById(long id);
    /**
     * 查询所有标签
     * @return 标签列表
     */
    @Select("select * from t_tag")
    List<Tag> selectAll();

    /**
     * 获取所有携带该标签的文章
     * @param id 标签id
     * @return 文章列表
     */
    @Select("select count(1) from t_article_tag_rel where tag_id = #{id}")
    int getArticles(long id);

    /**
     * 根据ArticleId查询标签
     * @param articleId 文章id
     * @return 标签列表
     */
    @Select("select t.* from t_tag t, t_article_tag_rel r where t.id = r.tag_id and r.article_id = #{articleId}")
    List<Tag> getTagsByArticleId(long articleId);

    /**
     * 根据标签id获取articleId列表
     * @param tagId 标签id
     * @return 文章id列表
     */
    @Select("select article_id from t_article_tag_rel where tag_id = #{tagId}")
    public Page<Long> getArticleIdListByTagId(Long tagId);
}
