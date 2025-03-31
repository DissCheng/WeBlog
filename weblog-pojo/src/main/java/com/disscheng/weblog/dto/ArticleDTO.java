package com.disscheng.weblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDTO implements Serializable {
    private Long id;
    private String title;
    private String cover;
    private String summary;
    private String content;
    private Long categoryId;
    private List<Long> tags;
}
