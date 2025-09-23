package com.disscheng.weblog.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Like {
    /**
     *
     *   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '点赞id',
     *   `author_id` bigint(20) unsigned NOT NULL COMMENT '作者id',
     *   `reply_id` bigint(20) unsigned NOT NULL COMMENT '回复评论id',
     *   `article_id` bigint(20) unsigned NOT NULL COMMENT '文章id',
     *   `content` varchar(120) NOT NULL DEFAULT '' COMMENT '评论内容',
     *   `likes` bigint(20) unsigned NOT NULL COMMENT '点赞数‘,
     *   `replies` bigint(20) unsigned NOT NULL COMMENT '评论数量’
     *   `isPrimary`
     *   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
     *   `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
     */
    private Long id;

    private Long author_id;

    private Long reply_id;

    private Long article_id;

    private String content;

    private Long likes;

    private Long unlikes;

    private Boolean isPrimary;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Boolean is_deleted;
}
