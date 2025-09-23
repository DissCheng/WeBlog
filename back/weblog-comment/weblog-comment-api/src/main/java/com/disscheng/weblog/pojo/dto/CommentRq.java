package com.disscheng.weblog.pojo.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentRq {
    private Long author_id;

    private Long reply_id;

    private Long article_id;

    private String content;

    private Boolean isPrimary;
}
