package com.disscheng.weblog.pojo.rq;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentQueryRq {
    private Long articleId;

    private Long replyId;

    private Integer pageNum;

    private Integer pageSize;

    private Boolean isPrimary;
}
