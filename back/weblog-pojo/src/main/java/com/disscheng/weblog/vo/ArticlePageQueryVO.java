package com.disscheng.weblog.vo;

import com.disscheng.weblog.entity.Article;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

import java.util.List;

@Data
@Builder
public class ArticlePageQueryVO implements Serializable {
    private long current;
    private long size;
    private long total;
    private long pages;
    private List<Article> data;
}
