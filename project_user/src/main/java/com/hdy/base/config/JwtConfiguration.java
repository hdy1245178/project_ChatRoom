package com.hdy.base.config;

import com.hdy.base.interceptor.JwtInterceptor;
import com.hdy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//配置拦截器
@Configuration
public class JwtConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor ;
    //   a/c/x/s/   a/**
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns( "/**" )
                .excludePathPatterns("/**/login");//因为login之后才会生成token
    }
    //jd   登录

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
