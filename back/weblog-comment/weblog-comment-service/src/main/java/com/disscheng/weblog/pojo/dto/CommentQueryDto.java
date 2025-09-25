package com.disscheng.weblog.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentQueryDto {
    private Long articleId;

    private Long replyId;

    private Integer offset;

    private Integer pageSize;

    private Boolean isPrimary;
}
