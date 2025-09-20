package com.disscheng.weblog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginVO implements Serializable {
    private long id;
    private String userName;
    private String jwtToken;
}
