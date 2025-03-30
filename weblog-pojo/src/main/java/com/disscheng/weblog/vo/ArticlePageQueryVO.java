package com.disscheng.weblog.vo;

import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.entity.Category;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticlePageQueryVO implements Serializable {
    private long total;
    private int current;
    private int size;
    private int pages;
    private List<Article> data;
}
