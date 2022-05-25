package com.lzh.graduationdesign.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName TokenUtil
 * @Author HackerLZH
 * @Date 2022/4/6 21:11
 * @Description
 */
public class TokenUtil {
    /**
     * token名
     */
    public static final String COOKIE_NAME_TOKEN = "token";
    /**
     * token过期时间，1天
     */
    public static final int TOKEN_EXPIRE = 3600 * 24;
    /**
     * 从Cookie中获取token
     * @Param [request]
     * @return java.lang.String
     */
    public static String getCookieToken(HttpServletRequest request){
        String token = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(COOKIE_NAME_TOKEN)){
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }
}
