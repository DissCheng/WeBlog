package com.disscheng.weblog.mapper;

import com.disscheng.weblog.pojo.dto.CommentQueryDto;
import com.disscheng.weblog.pojo.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    public void insertComment(Comment comment);

    @Delete(value = "DELETE FROM t_comment WHERE id=#{id} OR (reply_id=#{id} AND isPrimary=0)")
    public void deleteComment(Long id);

    public void updateComment(Comment comment);

    public List<Comment> queryComment(CommentQueryDto commentQueryDto);

    @Select(value = "SELECT * FROM t_comment WHERE id=#{id}")
    public Comment selectComment(Long id);
}
