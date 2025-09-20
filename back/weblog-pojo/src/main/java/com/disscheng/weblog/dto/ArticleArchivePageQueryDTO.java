package com.disscheng.weblog.dto;


import com.disscheng.weblog.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Month;
import java.util.List;

@Builder
@Data
public class ArticleArchivePageQueryDTO implements Serializable {

    private String month;
    private List<Article> articles;
}
