package com.lzh.graduationdesign.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lzh.graduationdesign.entity.*;
import com.lzh.graduationdesign.mapper.CommentMapper;
import com.lzh.graduationdesign.service.IPlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Author HackerLZH
 * @Date 2022/4/10 9:13
 * @Description
 */
@Api(value = "admin controller")
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IPlayerService playerService;
    @Autowired
    private CommentMapper commentMapper;

    @ApiOperation(value = "管理员欢迎页")
    @GetMapping("/welcome")
    public String doAdmin(Map<String, Object> map) {
        List<CommentVO> commentVO = commentMapper.getUserComment();
        map.put("commentVO", commentVO);
        return "admin/admin-welcome";
    }

    @ApiOperation(value = "球员添加")
    @PostMapping("/player/doAdd")
    @ResponseBody
    public Result<String> doAdd(@RequestBody Map<String, String> map) {
        if (playerService.findByName(map.get("name")) != null) {
            return Result.info(CodeMsg.PLAYER_EXISTED); //不能重复添加球员
        }
        LocalDate birthday = LocalDate.parse(map.get("birthday"));
        int year = birthday.getYear();
        Player player = new Player(map.get("name")
                , LocalDate.now().getYear() - year
                , birthday
                , map.get("birth_place"), map.get("team")
                , Integer.parseInt(map.get("enter_time"))
                , Integer.parseInt(map.get("played_time"))
                , map.get("retired")
                , Integer.parseInt(map.get("score"))
                , Integer.parseInt(map.get("rebound"))
                , Integer.parseInt(map.get("assist"))
                , Integer.parseInt(map.get("steal"))
                , Integer.parseInt(map.get("block")));
        if (playerService.save(player)){
            log.warn("添加成功！");
            return Result.info(CodeMsg.SUCCESS);
        }else{
            log.warn("添加失败！");
            return Result.info(CodeMsg.FAILED);
        }
    }

    @ApiOperation(value = "前往指定球员信息页")
    @GetMapping("/player/update")
    public String update(@RequestParam String name, Map<String, Player> map){
        Player player = playerService.findByName(name);
        map.put("player", player);
        return "admin/admin-player-update";
    }

    @ApiOperation("球员修改")
    @PutMapping("/player/doUpdate")
    @ResponseBody
    public Result<String> doUpdate(@RequestBody Map<String, String> map){
        try {
            UpdateWrapper<Player> wrapper = new UpdateWrapper<>();
            wrapper.eq("name", map.get("name"))
                    .set("age", Integer.parseInt(map.get("age")))
                    .set("birthday", LocalDate.parse(map.get("birthday")))
                    .set("birth_place", map.get("birth_place"))
                    .set("team", map.get("team"))
                    .set("enter_time", Integer.parseInt(map.get("enter_time")))
                    .set("played_time", Integer.parseInt(map.get("played_time")))
                    .set("retired", map.get("retired"))
                    .set("score", Integer.parseInt(map.get("score")))
                    .set("rebound", Integer.parseInt(map.get("rebound")))
                    .set("assist", Integer.parseInt(map.get("assist")))
                    .set("steal", Integer.parseInt(map.get("steal")))
                    .set("block", Integer.parseInt(map.get("block")));
            playerService.update(wrapper);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Result.info(CodeMsg.FAILED);
        }
        return Result.info(CodeMsg.SUCCESS);
    }

    @ApiOperation("球员删除")
    @DeleteMapping("/player/doDelete")
    @ResponseBody
    public Result<String> delete(@RequestParam String name){
        if (name.length() == 0){
            return Result.fail("球员名不能为空！", null);
        }
        Player player = playerService.findByName(name);
        if (player == null){
            return Result.fail("该球员不存在！", null);
        }
        playerService.removeById(player);
        return Result.success("删除成功！", null);
    }
}
