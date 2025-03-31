package com.disscheng.weblog.dto;
import com.disscheng.weblog.vo.ArticleFrontendVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class TagDTO implements Serializable {
    private List<String> tags;

    @Data
    public static class ArticleFrontendPageQueryVO implements Serializable {
            private long total;
            private int current;
            private int size;
            private int pages;
            private List<ArticleFrontendVO> data;

    }
}
