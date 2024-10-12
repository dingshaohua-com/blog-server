package com.dshvv.blogserver;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@MapperScan("com.dshvv.blogserver.mapper")
@EnableOpenApi
@SpringBootApplication
public class BlogServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogServerApplication.class, args);
//        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }
}
