package com.dshvv.blogserver.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *  跨域处理 （已经暂时不用了，因为高版本chrome跨域cors也无法写入cookie，只能让前端想办法处理跨域）
 *  参考： http://www.telami.cn/2019/springboot-resolve-cors/
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-07
 */
@Configuration
public class CorsConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}