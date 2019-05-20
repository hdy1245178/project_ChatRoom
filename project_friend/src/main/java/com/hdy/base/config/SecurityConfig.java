package com.hdy.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //开启加密共能
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    //设置加密的细节
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers( "/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable() ; //将全部加密功能禁止
         //www.jd.com
        //www.jd.com/show.jsp
        //https://kuaibao.jd.com/article?id=172140563
    }

    //指定需要给密码进行加密
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder() ;
    }





}
