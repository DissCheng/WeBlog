package com.disscheng.weblog.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Setting implements Serializable {
    /**id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
     `logo` varchar(120) NOT NULL DEFAULT '' COMMENT '博客Logo',
     `name` varchar(60) NOT NULL DEFAULT '' COMMENT '博客名称',
     `author` varchar(20) NOT NULL DEFAULT '' COMMENT '作者名',
     `introduction` varchar(120) NOT NULL DEFAULT '' COMMENT '介绍语',
     `avatar` varchar(120) NOT NULL DEFAULT '' COMMENT '作者头像',
     `github_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT 'GitHub 主页访问地址',
     `csdn_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT 'CSDN 主页访问地址',
     `gitee_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT 'Gitee 主页访问地址',
     `zhihu_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT '知乎主页访问地址',
     **/
    private long  id;
    private String logo;
    private String name;
    private String author;
    private String introduction;
    private String avatar;
    private String githubHomepage;
    private String csdnHomepage;
    private String giteeHomepage;
    private String zhihuHomepage;
}
