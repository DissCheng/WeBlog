package com.disscheng.weblog.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ArticleVO implements Serializable {
    /**"id": 12, // 文章 ID
     "title": "", // 文章标题
     "cover": "", // 文章封面
     "content": "", // 文章内容
     "summary": "", // 文章摘要
     "categoryId": 1, // 分类 ID
     "tagIds": [1, 2, 3], // 标签 ID 集合
     **/
    private long id;
    private String title;
    private String cover;
    private String content;
    private String summary;
    private long categoryId;
    private String categoryName;
    private List<Long> tagIds;
}
