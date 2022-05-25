package com.lzh.graduationdesign.mapper;

import com.lzh.graduationdesign.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from `user` where name = #{username}")
    User findByUsername(String username);

    @Select("select * from `user` where email = #{email}")
    User findByEmail(String email);
}
