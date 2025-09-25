package com.disscheng.weblog.pojo.vo;


import com.disscheng.weblog.pojo.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CommentQueryVo {
    private List<Comment> comment;
}
