package com.lzh.graduationdesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 状态码，错误码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeMsg {
    private int code;
    private String msg;
    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(200, "成功!");
    public static CodeMsg FAILED = new CodeMsg(555, "失败！");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法!");
    //登录模块 5002XX
    public static CodeMsg USER_NOT_LOGIN = new CodeMsg(500200, "用户未登录！");
    public static CodeMsg TOKEN_INVALID = new CodeMsg(500201, "token无效！");
    public static CodeMsg USERNAME_NOT_EXIST = new CodeMsg(500202, "网名不存在!");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500203, "密码错误！");
    public static CodeMsg PASSWORD_CORRECT = new CodeMsg(500206, "密码正确！");
    public static CodeMsg EMAIL_NOT_REGISTERED = new CodeMsg(500204, "邮箱未注册！");
    public static CodeMsg EMAIL_REGISTERED = new CodeMsg(500205, "邮箱已注册！");
    //注册模块 5003XX
    public static CodeMsg USERNAME_EXISTED = new CodeMsg(500300, "网名已存在！");
    //管理员模块 5004XX
    public static CodeMsg PLAYER_EXISTED = new CodeMsg(500400, "不能重复添加球员！");
}