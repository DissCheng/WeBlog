package com.disscheng.weblog.vo;

import com.disscheng.weblog.entity.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@Data
@Builder
public class ArticleFrontendDetailVO implements Serializable {

    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private long categoryId;
    private String categoryName;
    private long readNum;
    private List<Tag> tags;
    private HashMap<String ,Object> preArticle;
    private HashMap<String ,Object> nextArticle;
}
