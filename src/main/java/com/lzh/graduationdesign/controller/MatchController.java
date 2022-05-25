package com.lzh.graduationdesign.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzh.graduationdesign.entity.API;
import com.lzh.graduationdesign.entity.Result;
import com.lzh.graduationdesign.service.MatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MatchController
 * @Author HackerLZH
 * @Date 2022/4/15 17:46
 * @Description
 */
@Api("赛事信息获取")
@RequestMapping("/match")
@RestController
public class MatchController {
    @Autowired
    private MatchService matchService;

    @ApiOperation("获取近期赛程")
    @GetMapping("/getCalendar")
    public Result<JSONObject> calendar(){
        String url = API.NBA_CALENDAR;
        HttpMethod get = HttpMethod.GET;
        return Result.success("", matchService.client(url, get));
    }

    @ApiOperation("获取最新新闻")
    @GetMapping("/getNews")
    public Result<JSONObject> news(){
        String url = API.NBA_NEWS;
        HttpMethod get = HttpMethod.GET;
        return Result.success("", matchService.client(url, get));
    }
}
