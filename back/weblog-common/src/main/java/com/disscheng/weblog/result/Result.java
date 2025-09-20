package com.disscheng.weblog.result;

import java.io.Serializable;
import lombok.Data;

@Data
public class Result <T> implements Serializable {
	private Integer code;//编码
    private String msg;//错误信息
    private T data;//数据

    public static <T>Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(1);
        result.setData(data);
        return result;
    }
    public static <T>Result<T> success() {
        Result<T> result = new Result<T>();
        result.setCode(1);
        result.setData(null);
        return result;
    }
    public static <T>Result<T> error( String msg) {
        Result<T> result = new Result<T>();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
}
