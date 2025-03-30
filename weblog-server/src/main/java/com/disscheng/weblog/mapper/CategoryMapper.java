package com.disscheng.weblog.mapper;

import com.disscheng.weblog.dto.CategoryPageQueryDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.disscheng.weblog.entity.Category;

@Mapper
public interface CategoryMapper {
    /**
     * 分页查询分类列表
     * @param categoryPageQueryDTO 查询条件
     * @return
     */
    public Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) ;

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

}
