package com.disscheng.weblog.pojo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    /**
     *
     *   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
     *   `author_id` bigint(20) unsigned NOT NULL COMMENT '作者id',
     *   `nick_name` varchar(20) unsigned NOT NULL COMMENT '用户名'
     *   `reply_id` bigint(20) unsigned NOT NULL COMMENT '回复评论id',
     *   `article_id` bigint(20) unsigned NOT NULL COMMENT '文章id',
     *   `content` varchar(120) NOT NULL DEFAULT '' COMMENT '评论内容',
     *   `likes` bigint(20) unsigned NOT NULL COMMENT '点赞数‘,
     *   `replies` bigint(20) unsigned NOT NULL COMMENT '评论数量’,
     *   `isPrimary` tinyint(2) NOT NULL DEFAULT '0' COMMENT '一级评论：0：否 1：是',
     *   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
     *   `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
     */

    private Long id;
    //回复人
    private Long authorId;
    //回复人名称
    private String authorName;
    //回复对象
    private Long toAuthorId;
    //回复对象名称
    private String toAuthorName;
    //回复评论
    private Long replyId;
    //一级评论
    private Long rootId;
    //文章id
    private Long articleId;
    //文章内容
    private String content;
    //点赞数
    private Long likes;
    //回复数
    private Long replies;
    //是否为一级评论
    private Boolean isPrimary;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    //是否有效
    private Boolean isDeleted;
}
