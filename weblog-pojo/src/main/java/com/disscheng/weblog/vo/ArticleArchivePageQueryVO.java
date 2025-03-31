package com.disscheng.weblog.vo;


import com.disscheng.weblog.dto.ArticleArchivePageQueryDTO;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class ArticleArchivePageQueryVO implements Serializable {

    private long total;
    private long size;
    private long current;
    private long pages;
    private List<ArticleArchivePageQueryDTO> data;
}
