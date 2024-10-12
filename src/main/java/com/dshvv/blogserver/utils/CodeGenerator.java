package com.dshvv.blogserver.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.stereotype.Component;

import java.util.Collections;

public class CodeGenerator {
    public static String projectPath = System.getProperty("user.dir");
    public static String dateBaseUrl = "jdbc:mysql://dingshaohua.mysql.rds.aliyuncs.com:3306/blog?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false";
    public static void main(String[] args) {
        FastAutoGenerator.create(dateBaseUrl, "root", "@Dsh742308560")
                .globalConfig(builder -> {
                    builder.author("丁少华") // 设置作者
                            .enableSwagger() // 开启 swagger 模式（非必须，如果开启，需要在依赖添加swagger以及启动类加注解）
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath+"/src/main/java/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.dshvv.blogserver") // 设置父包名 我这里没有
                            .moduleName("") // 设置父包模块名 我这里没有
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath+"/src/main/resources/mapper/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user","article", "mood","type", "comment") // 设置需要生成的表名
                            .addTablePrefix(); // 设置过滤表前缀. 我的表没有前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}