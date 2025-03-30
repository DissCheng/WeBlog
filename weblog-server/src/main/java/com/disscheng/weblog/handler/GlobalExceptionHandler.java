package com.disscheng.weblog.handler;

import com.disscheng.weblog.exception.BaseException;
import com.disscheng.weblog.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result<String> handleBaseException(BaseException e) {

        return Result.error(e.getMessage());
    }
    @ExceptionHandler
    public Result<String> handleException(SQLException e) {
        if(e.getMessage().contains("Duplicate entry")){
            if(e.getMessage().contains("user")){
                return Result.error("用户名已存在");
            }
            if(e.getMessage().contains("uk_name")){
                return Result.error("分类名已存在");
            }
        }
        return Result.error(e.getMessage());
    }

}
