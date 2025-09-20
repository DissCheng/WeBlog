package com.disscheng.weblog.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class ArticleCategory implements Serializable {
    /**`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
     `article_id` bigint(20) unsigned NOT NULL COMMENT '文章id',
     `category_id` bigint(20) unsigned NOT NULL COMMENT '分类id',
     */
    private Long id;
    private Long articleId;
    private Long categoryId;
}
