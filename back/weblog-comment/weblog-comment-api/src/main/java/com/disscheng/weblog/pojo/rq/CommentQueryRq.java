package com.disscheng.weblog.pojo.rq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentQueryRq {
    private Long articleId;

    private Long replyId;

    private Integer pageNum;

    private Integer pageSize;

    private Boolean isPrimary;
}
