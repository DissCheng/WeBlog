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

    private String authorName;

    private Long toAuthorId;

    private String toAuthorName;

    private Long replyId;

    private Long rootId;

    private Long articleId;

    private String content;

    private Boolean isPrimary;
}
