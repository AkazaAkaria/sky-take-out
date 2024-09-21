package com.sky.interceptor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AkazaAkari
 * @version 1.0
 * @className JwtInterceporConfig
 * @description TODO
 * @date 2021/3/24 14:29
 */
@Configuration
public class JwtInterceporConfig implements WebMvcConfigurer {
    //注册自定义拦截器规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
       excludePath.add("/user_register"); //注册
       excludePath.add("/login");//登录
       excludePath.add("/loginout");//注销
       excludePath.add("/static/**");//静态资源
       excludePath.add("/assets/**"); //静态资源

        registry.addInterceptor(myInterceptor()).excludePathPatterns(excludePath).addPathPatterns("/**");
    }
    //创建自定义的拦截器对象,并交给spring管理
    @Bean
    public HandlerInterceptor myInterceptor(){
        return  new AUthenInterceptor();
    }
    @Bean
    public HandlerInterceptor othersInterceptor(){
        return  new OthersInterceptor();
    }

}
