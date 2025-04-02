package com.disscheng.weblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ArticleStatisticDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createDate;
    int count;
}
