package com.lzh.graduationdesign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lzh.graduationdesign.service.MatchService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName MatchServiceImpl
 * @Author HackerLZH
 * @Date 2022/4/16 10:18
 * @Description
 */
@Service
public class MatchServiceImpl implements MatchService {
    @Override
    public JSONObject client(String url, HttpMethod method) {
        RestTemplate template = new RestTemplate();
//        ResponseEntity<JSONObject> response = template.getForEntity(url, JSONObject.class);
        return template.getForObject(url, JSONObject.class);
    }
}
