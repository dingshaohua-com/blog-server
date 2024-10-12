package com.dshvv.blogserver.interceptor;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * <p>
 * SaToken全局拦截器，校验登录
 * 参考 https://www.cnblogs.com/yunchu/p/14304900.html、
 * 参考 https://www.cnblogs.com/yunchu/p/14362461.html (重点)
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-07
 */

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/*.html", "/**/*.js", "/**/*.css", "/**/favicon.ico") //排除静态资源
                .excludePathPatterns("/", "/login", "/reg","/uploadFile", "/error", "/sendVerifyCode", "/article/**", "/mood/**", "/type/**", "/masterInfo", "/log/**", "/comment/**", "/reg_login", "/img/**") //排除某些接口
                .excludePathPatterns("/swagger**/**", "/v3/**", "/doc.html", "/webjars/**"); //排除swagger设置
    }
}