package com.lzh.graduationdesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;
    /**
     * 通用消息体
     */
    public static <T> Result<T> success(String msg, T data){
        return new Result<T>(200, msg, data);
    }
    public static <T> Result<T> fail(String msg, T data){
        return new Result<T>(555, msg, data);
    }
    public static <T> Result<T> info(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }
}