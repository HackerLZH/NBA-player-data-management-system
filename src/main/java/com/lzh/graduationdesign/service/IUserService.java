package com.lzh.graduationdesign.service;

import com.lzh.graduationdesign.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
public interface IUserService extends IService<User> {
    /**
     * 根据网名获取User对象
     * @Param [username]
     * @return com.lzh.graduationdesign.entity.User
     */
    User findByUsername(String username);
    /**
     * 根据邮箱获取User对象
     * @Param [email]
     * @return com.lzh.graduationdesign.entity.User
     */
    User findByEmail(String email);
    /**
     * 登录处理
     * @Param [response, username]
     * @return java.lang.String
     */
    String login(HttpServletResponse response, String username);
    /**
     * 根据token从redis中获取User对象
     * @Param [token]
     * @return com.lzh.graduationdesign.entity.User
     */
    User getByToken(String token);
}
