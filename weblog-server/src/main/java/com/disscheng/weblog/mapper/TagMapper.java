package com.disscheng.weblog.mapper;

import com.disscheng.weblog.dto.TagPageQueryDTO;
import com.disscheng.weblog.entity.Tag;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
