package com.lzh.graduationdesign.config;

import com.lzh.graduationdesign.entity.CodeMsg;
import com.lzh.graduationdesign.entity.User;
import com.lzh.graduationdesign.exception.GlobalException;
import com.lzh.graduationdesign.service.IUserService;
import com.lzh.graduationdesign.utils.RedisUtil;
import com.lzh.graduationdesign.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 无业务逻辑跳转
     * @Param []
     * @return org.springframework.web.servlet.config.annotation.WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/index").setViewName("index");
                registry.addViewController("/user/register").setViewName("user/register");
                registry.addViewController("/admin/player/add").setViewName("admin/admin-player-add");
                registry.addViewController("/player/profile").setViewName("player/profile");
                registry.addViewController("/match/calendar").setViewName("match/calendar");
                registry.addViewController("/match/news").setViewName("match/news");
            }
        };
    }

    /**
     * 创建登录拦截器
     * @Param []
     * @return org.springframework.web.servlet.HandlerInterceptor
     */
    @Bean
    public HandlerInterceptor loginInterceptor(){
        return new HandlerInterceptor(){
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String token = TokenUtil.getCookieToken(request);
                try {
                    if (token.equals("")){
                        throw new GlobalException(CodeMsg.USER_NOT_LOGIN);
                    }else if (redisUtil.get(TokenUtil.COOKIE_NAME_TOKEN + "::" + token).equals("")){
                        throw new GlobalException(CodeMsg.TOKEN_INVALID);
                    }
                } catch (GlobalException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        };
    }

    /**
     * 创建admin拦截器
     * @Param []
     * @return org.springframework.web.servlet.HandlerInterceptor
     */
    @Value("${admin.email}")
    String adminEmail;
    @Bean
    public HandlerInterceptor adminInterceptor() {
        return new HandlerInterceptor(){
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                User user = userService.getByToken(TokenUtil.getCookieToken(request));
                if (!user.getEmail().equals(adminEmail)){
                    throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
                }
                return true;
            }
        };
    }

    /**
     * 注册拦截器
     * @Param [registry]
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(
                        "/assets/**", "/favicon.ico",
                        "/", "/index", "/user/**");
        registry.addInterceptor(adminInterceptor()).addPathPatterns("/admin/**");
    }
}