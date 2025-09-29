package com.disscheng.weblog.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Like {
    /**
     *
     *   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '点赞id',
     *   `user_id` bigint(20) unsigned NOT NULL COMMENT '作者id',
     *   `reply_id` bigint(20) unsigned NOT NULL COMMENT '回复评论id',
     */

    private Long id;

    private Long userId;

    private Long replyId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime likeTime;
}
