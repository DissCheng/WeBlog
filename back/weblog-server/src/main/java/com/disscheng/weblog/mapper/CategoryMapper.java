package com.disscheng.weblog.mapper;

import com.disscheng.weblog.dto.CategoryPageQueryDTO;
import com.github.pagehelper.Page;
import io.minio.ObjectWriteArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.disscheng.weblog.entity.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {


    /**
     * 获取分类总数
     * @return
     */
    @Select("select count(*) from t_category")
    public int count();



    /**
     * 分页查询分类列表
     * @param categoryPageQueryDTO 查询条件
     * @return
     */
    public Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) ;
    /**
     * 查询所有分类
     * @return
     */
    @Select("select * from t_category")
    public Page<Category> getAll();
    /**
     * 新增分类
     * @param category 分类对象
     * @return
     */
    @Insert("insert into t_category(name,create_time,update_time,is_deleted) " +
            "values(#{name},#{createTime},#{updateTime},#{isDeleted})")
    public int add(Category category);
    /**
     * 删除分类
     * @param id 分类id
     * @return
     */
    @Delete("delete from t_category where id = #{id}")
    public int delete(Long id);
    /**
     * 获取所有属于该分类的文章
     * @param id 分类id
     * @return
     */
    @Select("select count(*) from t_article_category_rel where category_id = #{id} limit 1")
    public int getArticles(Long id);

    /**
     * 获取分类名
     * @param id 分类id
     */
    @Select("select name from t_category where id = #{id}")
    public String getName(Long id);


    /**
     * 根据articleId获取分类名和分类id
     * @param articleId 文章id
     */
    @Select("select c.* from t_category c,t_article_category_rel r where r.article_id = #{articleId} and r.category_id = c.id")
    public Category getCategoryByArticleId(Long articleId);

    /**
     * 根据分类id获取articleId列表
     * @param categoryId 分类id
     */
    @Select("select article_id from t_article_category_rel where category_id = #{categoryId}")
    public Page<Long> getArticleIdListByCategoryId(Long categoryId);
}
