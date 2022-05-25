package com.lzh.graduationdesign.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName CommentVO
 * @Author HackerLZH
 * @Date 2022/4/17 23:50
 * @Description admin中展示用户留言需要的字段(来自于user表和comment表)
 */
@Data
public class CommentVO {
    private String name;
    private String icon;
    private Timestamp commentTime;
    private String text;
}
