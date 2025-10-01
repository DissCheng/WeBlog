package com.disscheng.weblog.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Data
public class CommentQueryDto {
    private Long articleId;

    private Long replyId;

    private Long rootId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cursor;

    private Integer offset;

    private Integer pageSize;

    private Boolean isPrimary;
}
