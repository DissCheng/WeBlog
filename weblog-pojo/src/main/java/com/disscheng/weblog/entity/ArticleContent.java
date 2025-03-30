package com.disscheng.weblog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleContent implements Serializable {

    /**
     *
     *`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章内容id',
     *   `article_id` bigint(20) NOT NULL COMMENT '文章id',
     *   `content` text COMMENT '教程正文',
     */
    private Long id;
    private Long articleId;
    private String content;
}
