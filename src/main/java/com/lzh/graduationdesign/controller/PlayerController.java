package com.lzh.graduationdesign.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzh.graduationdesign.entity.Player;
import com.lzh.graduationdesign.entity.PlayerPage;
import com.lzh.graduationdesign.entity.Result;
import com.lzh.graduationdesign.service.IPlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
@Api(value = "player controller")
@Slf4j
@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @ApiOperation("球员信息表")
    @PostMapping("/profile/table")
    @ResponseBody
    public PlayerPage<Player> playerList(int page, int limit){
        //传入分页的属性
        Page<Player> pager = new Page<>(page,limit);
        //分页查询球员信息
        IPage<Player> playerPage = playerService.page(pager, new QueryWrapper<>());
        // schoolPage.getTotal() 信息总条数
        // schoolPage.getRecords() 分页数据
        return new PlayerPage<>(playerPage.getTotal(), playerPage.getRecords());
    }

    @ApiOperation("检查球员信息是否存在")
    @GetMapping("/profile/check")
    @ResponseBody
    public Result<String> check(@RequestParam String name){
        if (name.length() == 0){
            return Result.fail("球员名不能为空！", null);
        }
        Player player = playerService.findByName(name);
        if (player == null){
            return Result.fail("该球员不存在，请及时添加！", null);
        }
        return Result.success("正在进入" + name + "的信息页...", null);
    }

    @ApiOperation("球员数据图")
    @GetMapping("/data/graph")
    public String dataRank(Map<String, Object> map){
        List<Player> player1 = playerService.selectTenOrderByScoreDesc();
        List<Integer> num1 = playerService.selectNumByPlayedTime();
        map.put("player1", player1);
        map.put("num1", num1);
        return "player/graph";
    }
}

