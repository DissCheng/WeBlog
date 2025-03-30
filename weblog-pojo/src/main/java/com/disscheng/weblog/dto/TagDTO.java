package com.disscheng.weblog.dto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class TagDTO implements Serializable {
    private List<String> tags;
}
