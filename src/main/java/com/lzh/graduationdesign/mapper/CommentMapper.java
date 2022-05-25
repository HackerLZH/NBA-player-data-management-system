package com.lzh.graduationdesign.mapper;

import com.lzh.graduationdesign.entity.Comment;
import com.lzh.graduationdesign.entity.CommentVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @InterfaceName CommentMapper
 * @Author HackerLZH
 * @Date 2022/4/17 23:27
 * @Description
 */
public interface CommentMapper {
    /**
     * 保存用户留言
     * @Param [comment]
     * @return void
     */
    @Insert("insert into `comment`(`user_id`, `comment_time`, `text`) values(#{userId}, #{commentTime}, #{text})")
    void save(Comment comment);

    /**
     * 获得CommentVO，用于前端展示
     * @Param []
     * @return java.util.List<com.lzh.graduationdesign.entity.CommentVO>
     */
    @Select("select name, icon, comment_time, text from user, comment " +
            "where user.id = comment.user_id order by comment_time")
    List<CommentVO> getUserComment();
}
