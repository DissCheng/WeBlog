package com.disscheng.weblog.pojo.rq;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddRq {
    private Long authorId;

    private Long replyId;

    private Long articleId;

    private String content;

    private Boolean isPrimary;
}
