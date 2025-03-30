package com.disscheng.weblog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CategoryDTO implements Serializable {
    private String logo;
    private String name;
    private String author;
    private String introduction;
    private String avatar;
    private String githubHomepage;
    private String csdnHomepage;
    private String giteeHomepage;
    private String zhihuHomepage;
}
