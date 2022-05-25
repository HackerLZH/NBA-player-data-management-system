package com.lzh.graduationdesign.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lzh.graduationdesign.entity.CodeMsg;
import com.lzh.graduationdesign.entity.User;
import com.lzh.graduationdesign.exception.GlobalException;
import com.lzh.graduationdesign.mapper.UserMapper;
import com.lzh.graduationdesign.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.graduationdesign.utils.RedisUtil;
import com.lzh.graduationdesign.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
@Service
//@CacheConfig(cacheNames = "user", keyGenerator = "keyGenerator")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

//    @Cacheable(value="username", key="#username")
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

//    @Cacheable(value="email", key="#email")
    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public String login(HttpServletResponse response, String email) {
        User user = findByEmail(email);
        //生成cookie
        String token = UUID.randomUUID().toString().replace("-", "");
        addCookie(response, token, user);
        return token;
    }

//    @Cacheable(value="token", key="#token")
    @Override
    public User getByToken(String token) {
        return JSON.parseObject(redisUtil.get(TokenUtil.COOKIE_NAME_TOKEN + "::" + token), User.class);
    }
    private void addCookie(HttpServletResponse response, String token, User user) {
        //将token存入到redis
        redisUtil.set(TokenUtil.COOKIE_NAME_TOKEN + "::" + token, /*序列化*/JSON.toJSONString(user), TokenUtil.TOKEN_EXPIRE);
        //将token写入cookie
        Cookie cookie = new Cookie(TokenUtil.COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(TokenUtil.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
