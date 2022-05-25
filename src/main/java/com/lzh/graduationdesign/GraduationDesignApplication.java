package com.lzh.graduationdesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableCaching //开启缓存
@MapperScan("com.lzh.graduationdesign.mapper")
@SpringBootApplication
public class GraduationDesignApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GraduationDesignApplication.class);
        application.setBannerMode(Banner.Mode.OFF); //关闭banner
        application.run(args);
    }
}
