package com.disscheng.weblog.pojo.rq;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentAddRq {
    private Long author_id;

    private Long reply_id;

    private Long article_id;

    private String content;

    private Boolean isPrimary;
}
