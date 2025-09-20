package com.disscheng.weblog.vo;

import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.entity.Category;
import com.disscheng.weblog.entity.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ArticleFrontendVO implements Serializable {
    public long id;
    public String title;
    public String cover;
    public String summary;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime createTime;
    public Category category;
    public List<Tag> tags;
}
