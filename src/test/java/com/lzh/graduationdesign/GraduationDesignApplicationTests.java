package com.lzh.graduationdesign;

import com.lzh.graduationdesign.entity.API;
import com.lzh.graduationdesign.entity.Comment;
import com.lzh.graduationdesign.entity.Player;
import com.lzh.graduationdesign.mapper.CommentMapper;
import com.lzh.graduationdesign.mapper.PlayerMapper;
import com.lzh.graduationdesign.service.MailService;
import com.lzh.graduationdesign.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class GraduationDesignApplicationTests {
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    MailService mailService;
    @Autowired
    MatchService matchService;
    @Autowired
    CommentMapper commentMapper;

    @Test
    void contextLoads() {
//        System.out.println(System.currentTimeMillis());

//        List<Player> players = playerMapper.selectTenOrderByScoreDesc();
//        for (Player player : players)
//            System.out.println(player);

//        System.out.println(playerMapper.selectNumByPlayedTime());

//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println(timestamp);

//        Comment comment = new Comment();
//        comment.setUserId(1);
//        comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
//        comment.setText("123");
//        commentMapper.save(comment);
        System.out.println(commentMapper.getUserComment());
    }

    @Test
    public void testEmail(){
        String to = "lzh1064433607@163.com";
        mailService.send(to, "NBA数据管理系统的验证码邮件", UUID.randomUUID().toString().toUpperCase());
    }

    @Test
    public void testMyIP(){
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            System.out.println(host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCORS(){
        String url = API.NBA_NEWS;
        HttpMethod get = HttpMethod.GET;
        System.out.println(matchService.client(url, get));
    }
}
