package com.disscheng.weblog.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ArticleTag implements Serializable {
    /*`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` bigint(20) unsigned NOT NULL COMMENT '文章id',
  `tag_id` bigint(20) unsigned NOT NULL COMMENT '标签id',*/
    private Long id;
    private Long articleId;
    private Long tagId;
}
