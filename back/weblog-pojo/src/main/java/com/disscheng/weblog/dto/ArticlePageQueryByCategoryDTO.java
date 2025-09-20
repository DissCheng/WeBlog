package com.disscheng.weblog.dto;


import lombok.Data;

@Data
public class ArticlePageQueryByCategoryDTO {
    private int current;
    private int size;
    private long id;
}
