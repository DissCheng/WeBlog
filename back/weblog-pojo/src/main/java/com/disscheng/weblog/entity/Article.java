package com.disscheng.weblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class Article implements Serializable {
    /**
     *
     *`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章id',
     *   `title` varchar(120) NOT NULL DEFAULT '' COMMENT '文章标题',
     *   `cover` varchar(120) NOT NULL DEFAULT '' COMMENT '文章封面',
     *   `summary` varchar(160) DEFAULT '' COMMENT '文章摘要',
     *   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
     *   `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
     *   `read_num` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '被阅读次数'
     *
     */
    private Long id;
    private String title;
    private String cover;
    private String summary;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private Integer isDeleted;
    private Integer readNum;
}
