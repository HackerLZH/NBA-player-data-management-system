package com.lzh.graduationdesign.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;

/**
 * @InterfaceName MatchService
 * @Author HackerLZH
 * @Date 2022/4/16 10:17
 * @Description
 */
public interface MatchService {
    /**
     * 跨域请求
     * @Param [url, method]
     * @return com.alibaba.fastjson.JSONObject
     */
    JSONObject client(String url, HttpMethod method);
}
