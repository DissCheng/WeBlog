package com.disscheng.weblog.mapper;

import com.disscheng.weblog.pojo.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommentMapper {

    public void insertComment(Comment comment);

    @Delete(value="DELETE FROM t_comment WHERE id=#{id} OR")
    public void deleteComment(Long id);

    @Update(value = "UPDATE FROM t_comment WHERE id=#{id}")
    public void updateComment(Comment comment);
}
