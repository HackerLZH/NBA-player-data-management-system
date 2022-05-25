package com.lzh.graduationdesign.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @ClassName MPGenerator
 * @Author HackerLZH
 * @Date 2022/4/4 16:21
 * @Description MyBatis-Plus代码生成器
 */
public class MPGenerator {
    private static final String URL = "jdbc:mysql://localhost:3306/graduationdesign?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8";
    public static void main(String[] args) {
        FastAutoGenerator.create(URL, "root", "lzh420821")
                //全局配置
                .globalConfig(builder -> {
                    builder.author("HackerLZH") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:/test/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.lzh") // 设置父包名
                            .moduleName("graduationdesign"); // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:/test/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user", "player_profile", "player_data"); // 设置需要生成的表名
//                            .addTablePrefix("player_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) //默认的是Velocity引擎模板
                .execute();

    }
}
